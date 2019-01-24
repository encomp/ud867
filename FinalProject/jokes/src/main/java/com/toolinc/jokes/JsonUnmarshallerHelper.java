package com.toolinc.jokes;

import com.google.common.annotations.VisibleForTesting;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Helper class to unrmashall json files. */
final class JsonUnmarshallerHelper {
  private JsonUnmarshallerHelper() {}

  static final Joke toJoke(Class clazz, String fileName) {
    try {
      return Joke.builder().fromJson(toString(clazz, fileName));
    } catch (IOException exception) {
      throw new IllegalStateException("Unable to unmarshal into a step instance.");
    }
  }

  @VisibleForTesting
  static String toString(Class clazz, String fileName) {
    try {
      URL url = clazz.getResource(fileName);
      Path path = Paths.get(url.toURI());
      return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    } catch (URISyntaxException | IOException exception) {
      throw new IllegalStateException("Unable to unmarshal into a json instance.");
    }
  }
}
