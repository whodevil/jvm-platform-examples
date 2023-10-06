import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.name.Named
import graphql.GraphQL
import graphql.schema.idl.*
import info.offthecob.examples.library.DgsConstants.QUERY.Words
import info.offthecob.examples.library.DgsConstants.QUERY_TYPE
import io.javalin.Javalin
import org.apache.commons.io.IOUtils
import java.io.IOException
import java.nio.charset.StandardCharsets

const val GRAPHQL_SCHEMA = "graphql-schema"
const val PORT = "port"

class ServiceModule : AbstractModule() {

    @Provides
    @Singleton
    fun javalin(): Javalin {
        return Javalin.create { config ->
            config.staticFiles.add { staticFiles ->
                staticFiles.directory = "/static"
            }
        }
    }

    @Provides
    @Named(GRAPHQL_SCHEMA)
    @Throws(IOException::class)
    fun graphQlSchema(): String {
        ServiceModule::class.java.getResourceAsStream("/schema.graphql").use { inputStream ->
            return IOUtils.toString(
                inputStream,
                StandardCharsets.UTF_8,
            )
        }
    }

    @Provides
    @Singleton
    fun runtimeWiringBuilder(dataFetchers: DataFetchers): RuntimeWiring {
        return RuntimeWiring
            .newRuntimeWiring()
            .type(QUERY_TYPE) { builder ->
                builder.dataFetcher(Words, dataFetchers.getWords())
                builder
            }.build()
    }

    @Provides
    @Singleton
    @Throws(IOException::class)
    fun typeDefinitionRegistryProvider(@Named(GRAPHQL_SCHEMA) schema: String): TypeDefinitionRegistry {
        return SchemaParser().parse(schema)
    }

    @Provides
    @Singleton
    fun graphQL(
        typeDefinitionRegistry: TypeDefinitionRegistry,
        schemaGenerator: SchemaGenerator,
        runtimeWiring: RuntimeWiring,
    ): GraphQL {
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
        return GraphQL.newGraphQL(graphQLSchema).build()
    }
}
