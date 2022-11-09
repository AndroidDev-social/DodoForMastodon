# Mastodon compose client

[![CC-BY license](https://img.shields.io/badge/License-CC--BY-blue.svg)](https://creativecommons.org/licenses/by-nd/4.0)
![CI](https://github.com/thebino/MastodonCompose/workflows/CI/badge.svg)

A multiplatform Mastodon client written in [Kotlin](kotlinlang.org) for the
amazing [androiddev.social](https://androiddev.social) community and everyone else who enjoys
#Fediverse

[F-Droid]
[Google Play]
[Apple Appstore]

---

## Getting started

The app is not published nor ready for use. Later on it will probably just be installed from any of
the available sources.

## Contribution

See our [Contribution Guidelines](CONTRIBUTING.md)

## Roadmap

Building an MVP with base functionality

* Sign up flow (for new users withoug account)
* Select an instance [joinmastodon servers](https://joinmastodon.org/de/servers)
* Sign in with credentials
* Browse timeline
* Create a Toot!

## Verifications

### Detekt

For code analysis and enforcing code guidelines. Run this before your open a PR

1. Run for whole project: `./gradlew detekt`
2. Run for specific module: `./gradlew module:detekt`
 * Sign up flow (for new users without account)
 * Select an instance [joinmastodon servers](https://joinmastodon.org/de/servers)
 * Sign in with credentials
 * Browse timeline
 * Create a Toot!

## License

Some community friendly license, not decided which one :)

See the [License discussion](https://github.com/AndroidDev-social/MastodonCompose/discussions/23)
