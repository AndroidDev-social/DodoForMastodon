# Mastodon compose client
[![CC-BY license](https://img.shields.io/badge/License-CC--BY-blue.svg)](https://creativecommons.org/licenses/by-nd/4.0)
![CI](https://github.com/thebino/MastodonCompose/workflows/CI/badge.svg)

A multiplatform Mastodon client written in [Kotlin](kotlinlang.org) for the amazing [androiddev.social](https://androiddev.social) community and everyone else who enjoys #Fediverse

[F-Droid]
[Google Play]
[Apple Appstore]

---

## Getting started

The app is not published nor ready for use. Later on it will probably just be installed from any of the available sources.


## Contribution

See our [Contribution Guidelines](CONTRIBUTING.md)

## MVP Roadmap

Building an MVP with base functionality for Desktop and Android

Progress of MVP can be found [here](https://github.com/AndroidDev-social/MastodonCompose/milestones)

 * Sign up
   * Select an instance [joinmastodon servers](https://joinmastodon.org/de/servers)
 * Sign in with credentials
 * Browse *local* timeline
 * Create a Toot!
 * Log out

## MVP contributions

Before tons of developers pour in to start contributing, it would be nice to get the codebase into a stable working condition with some sort of consistent architecture and design. In order to do this it would be more optimal to limit the # of devs working on each task under the MVP roadmap. Each task can be picked up by a single dev who does the full end-to-end functionality. Or, 2-3 devs can split up the work by having one person pick up the data/api layer, one on android and one on desktop. This small group can act like it's own "sprint team" and collaborate closely to split up the work and coordinate efforts to avoid duplicating effort. Since only one person can be assigned an issue, that person can be the feature "lead" and the other 1-2 devs can add a comment to the issue as the other contributors.

## Non-MVP contributions

There is plenty of [other issues to work on outside of MVP](https://github.com/AndroidDev-social/MastodonCompose/issues) and these can be started by any contributor. Please assign the issue to yourself for visibility. 
Alternatively, there can be contributions made that aren't code related.
- Innovative ideas for possible features that don't exist in other existing clients that [require discussion here](https://github.com/AndroidDev-social/MastodonCompose/discussions/categories/ideas)
- General question, gotchas and other discussions [here](https://github.com/AndroidDev-social/MastodonCompose/discussions/categories/general)
- Create new Issues/Features for post MVP. (e.g. Material You support, animations, V2 functionality, migrations, etc)

## Verifications

### Detekt

For code analysis and enforcing code guidelines. Run this before your open a PR

1. Run for whole project: `./gradlew detekt`
2. Run for specific module: `./gradlew module:detekt`

## License

Some community friendly license, not decided which one :)

See the [License discussion](https://github.com/AndroidDev-social/MastodonCompose/discussions/23)
