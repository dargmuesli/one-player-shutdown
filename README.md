[![build status](https://travis-ci.com/dargmuesli/one-player-shutdown.svg?branch=master)](https://travis-ci.com/one-player-shutdown/1generator "build status")

# OnePlayerShutdown
Automatically shuts a Minecraft server down when only one player is connected.

## Download
See [Releases](https://github.com/Dargmuesli/one-player-shutdown/releases "Releases").

## Installation
Just put the downloaded .jar into your `plugins` folder and `start` or `reload` your server!

## What this plugin does
Whenever a player is playing alone on a Minecraft Server this plugin enables the server to announce a server shutdown if nobody else joins within two minutes. The time limit can be changed by the user and the plugin can be disabled and enabled again through the `/oneplayershutdown` command.

## Why
For me multiplayer servers are there for people to play with one another. To make sure nobody feels lonely on my Minecraft server I decided to write this plugin to prevent excessive singleplayer gaming on a multiplayer server.

## Commands
- `oneplayershutdown` - Commands for configuring automatic shutdown.
  - `/<command> [enable, start]` - enable One-Player-Shutdown.
  - `/<command> [disable, stop]` - disable One-Player-Shutdown.
  - `/<command> status` - displays the current plugin configuration.
  - `/<command> timelimit <seconds>` - set the shutdown's timelimit.
  - `/<command> reset` - resets this plugin's configuration.

## Default Settings
- `enabled: true`
- `timelimit: 180`
