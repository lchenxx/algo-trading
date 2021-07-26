package algotrading.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public interface FileManager {
  default List<String> load(String fileName) {
    try {
      File file = new File(fileName);
      return Arrays.asList(FileUtils.readFileToString(file, StandardCharsets.UTF_8).split(","));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
