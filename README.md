# Path Redirect Plugin

A tiny plugin for Reposilite 3 that redirects repository-less urls to the main repository.

## Usage

1. Insert jar file into `/plugins` directory
2. Set incoming path prefix via `PATH_REDIRECTS` environment variable:
    1. The format is `<mapping>=<repository>`, `<mapping>` is any valid Javalin path (see: [here](https://javalin.io/documentation#endpoint-handlers))
    2. Example: `/com*=/releases`, this would redirect all urls starting with `/com` to `/releases/com`
    3. You can supply multiple redirects as a comma-separated list
3. Profit!

## License

[MIT License](./LICENSE)
