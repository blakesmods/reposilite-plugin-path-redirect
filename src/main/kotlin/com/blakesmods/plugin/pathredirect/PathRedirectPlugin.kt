package com.blakesmods.plugin.pathredirect

import com.reposilite.plugin.api.Facade
import com.reposilite.plugin.api.Plugin
import com.reposilite.plugin.api.ReposilitePlugin
import com.reposilite.plugin.event
import com.reposilite.shared.extensions.uri
import com.reposilite.web.api.HttpServerInitializationEvent

data class Redirect(
    val from: String,
    val to: String
)

@Plugin(name = "path-redirect")
class PathRedirectPlugin : ReposilitePlugin() {
    override fun initialize(): Facade? {
        val redirectKeys = System.getenv("PATH_REDIRECTS") ?: throw Error("Missing PATH_REDIRECTS environment variable")

        logger.info("")
        logger.info("--- Path Redirect Plugin")

        val redirects = redirectKeys.split(',').map {
            val parts = it.split('=')
            val from = parts[0]
            val to = parts[1]

            logger.info("Redirecting $from to $to$from")

            Redirect(from, to)
        }

        event { event: HttpServerInitializationEvent ->
            redirects.forEach { redirect ->
                event.config.router.mount {
                    it.before(redirect.from) { ctx ->
                        ctx.redirect(redirect.to + ctx.uri())
                    }
                }
            }
        }

        return null
    }
}