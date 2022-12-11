package social.androiddev.common.persistence.localstorage

interface DodoKeyValueStorage {
    var currentDomain: String?
}

expect fun getKeyValueStorage(): DodoKeyValueStorage