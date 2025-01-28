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
      // language=kotlin
      """
@MyIntArray([1, 2, 3])
fun test() = Unit
      """.trimIndent(),
    )
      .compile()
  }

  private fun prepareCompilation(vararg sources: String): KotlinCompilation =
    KotlinCompilation().apply {
      this.sources = sources.map { SourceFile.kotlin("test.kt", it, trimIndent = false) }
      workingDir = dir.toFile()
      inheritClassPath = true
      useKsp2()
      kspIncremental = true
      symbolProcessorProviders = mutableListOf(TestProcessor.Provider())
    }
}