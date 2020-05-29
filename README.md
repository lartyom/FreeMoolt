## How to use
* Download ffmpeg if not installed.
* Run it using command-line arguments: 
  * `path to .m3u8 playlist`
  * ~~`path to ffmpeg library`~~
 OpenJDK 11 no longer support JavaFX, so install OpenJFX and run the program with parameters:
 `--module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml`
* After download video files will be located in folder where located ffmpeg.

## Links for download
* Mult bank (needed for download videos from playlist): https://b1.mult.digitala.ru/c/
* `https://mult.digitala.ru/api/v1/materials?type=episode&order_by=&order_direction=&movie_id=7`
  * `order_by=<episode_number|published_at>` 
  * `order_direction=<desc|asc>` - `desc` for newest series, `asc` for all episodes.
  * `movie_id=<number>` - id for movies of Mult channel, in this case it's "Fantasy patrol".
* `https:\/\/mult.digitala.ru\/api\/v1\/materials?type=episode&movie_id=7&query=<query>`
  * `query` - query for series search.
* Other links are located on [URLS.java](https://pastebin.com/67SerUMV) file from decompilated app
