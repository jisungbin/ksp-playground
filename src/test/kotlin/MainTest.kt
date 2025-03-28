@file:OptIn(ExperimentalCompilerApi::class)

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.symbolProcessorProviders
import com.tschuchort.compiletesting.useKsp2
import java.nio.file.Path
import kotlin.test.Test
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.junit.jupiter.api.io.TempDir

class MainTest {
  @field:TempDir lateinit var dir: Path

  @Test fun test() {
    prepareCompilation(
      """
package test.test2

import MyTest

@MyTest
class Test2
      """.trimIndent(),
    )
      .compile()
  }

  private fun prepareCompilation(source: String): KotlinCompilation =
    KotlinCompilation().apply {
      this.sources = listOf(SourceFile.kotlin("test.kt", source, trimIndent = false))
      workingDir = dir.toFile()
      inheritClassPath = true
      useKsp2()
      kspIncremental = true
      symbolProcessorProviders = mutableListOf(TestProcessor.Provider())
    }
}