import com.google.gson.Gson
import com.google.inject.Inject
import graphql.ExecutionInput
import graphql.ExecutionResult
import graphql.GraphQL
import io.javalin.http.Context
import io.javalin.http.Handler

data class GraphqlRequest(
    val query: String,
    val variables: Map<String, Any>?,
    val operationName: String?,
)

class GraphQLHandler @Inject constructor(
    private val graphql: GraphQL,
    private val gson: Gson,
) : Handler {
    override fun handle(ctx: Context) {
        if (ctx.body().isEmpty()) {
            ctx.status(400)
            ctx.result("Bad Request")
        } else {
            handleGraphqlRequest(ctx)
        }
    }

    private fun handleGraphqlRequest(ctx: Context) {
        val graphqlRequest = gson.fromJson(ctx.body(), GraphqlRequest::class.java)
        val executionInput = executionInput(graphqlRequest, ctx)
        val executionResult = graphql.execute(executionInput)
        handleGraphqlExecutionResult(executionResult, ctx)
    }

    private fun handleGraphqlExecutionResult(executionResult: ExecutionResult, ctx: Context) {
        if (executionResult.isDataPresent) {
            ctx.result(gson.toJson(executionResult.toSpecification()))
            ctx.status(200)
        } else {
            ctx.result("Bad Request")
            ctx.status(400)
        }
    }

    private fun executionInput(graphqlRequest: GraphqlRequest, ctx: Context): ExecutionInput {
        val executionInputBuilder = ExecutionInput
            .newExecutionInput(graphqlRequest.query)
            .context(ctx)
        variables(graphqlRequest, executionInputBuilder)
        operationName(graphqlRequest, executionInputBuilder)
        return executionInputBuilder.build()
    }

    private fun operationName(graphqlRequest: GraphqlRequest, executionInputBuilder: ExecutionInput.Builder) {
        if (graphqlRequest.operationName?.isNotEmpty() == true) {
            executionInputBuilder.operationName(graphqlRequest.operationName)
        }
    }

    private fun variables(graphqlRequest: GraphqlRequest, executionInputBuilder: ExecutionInput.Builder) {
        if (graphqlRequest.variables?.isNotEmpty() == true) {
            executionInputBuilder.variables(graphqlRequest.variables)
        }
    }
}
