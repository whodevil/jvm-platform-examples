import com.google.inject.Guice
import com.google.inject.Inject
import dev.misfitlabs.kotlinguice4.getInstance
import io.javalin.Javalin

fun main() {
    Guice
        .createInjector(ServiceModule())
        .getInstance<ServiceMain>()
        .apply {
            go()
        }
}

class ServiceMain @Inject constructor(
    private val javalin: Javalin,
    private val graphQLHandler: GraphQLHandler,
) {
    fun go() {
        javalin
            .post("/graphql", graphQLHandler)
            .start()
    }
}
