package com.blakesmods.plugin.pathrewrite

import com.reposilite.plugin.api.Facade
import com.reposilite.plugin.api.Plugin
import com.reposilite.plugin.api.ReposilitePlugin
import com.reposilite.plugin.event
import com.reposilite.shared.extensions.uri
import com.reposilite.web.api.HttpServerInitializationEvent

@Plugin(name = "path-redirect")
class PathRedirectPlugin : ReposilitePlugin() {
    override fun initialize(): Facade? {
        val from = System.getenv("REDIRECT_FROM") ?: "/com*"
        val to = System.getenv("REDIRECT_TO") ?: "/releases"

        logger.info("")
        logger.info("--- Path Redirect Plugin")
        logger.info("Redirecting $from to $to$from")

        event { event: HttpServerInitializationEvent ->
            event.javalin.before(from) { ctx ->
                ctx.redirect("$to${ctx.uri()}")
            }
        }

        return null
    }
}