package social.androiddev.common.network

import social.androiddev.common.network.model.Instance

interface MastodonApi {
    /**
     * Fetch information about the server
     *
     * @see https://docs.joinmastodon.org/methods/instance/
     */
    suspend fun getInstance(domain: String? = null): Result<Instance>
}
