@file:OptIn(KspExperimental::class)

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated

class TestProcessor : SymbolProcessor {
  private var invoked = false

  override fun process(resolver: Resolver): List<KSAnnotated> {
    if (invoked) return emptyList() // Only need a single round.
    invoked = true

    resolver.getSymbolsWithAnnotation(MyTest::class.qualifiedName!!)
      .forEach { symbol ->
        println("Found @MyTest with value: $symbol")
      }

    resolver.getDeclarationsFromPackage("test.*")
      .toList()
      .let { println("TEST: " + it.joinToString { it.qualifiedName?.asString().toString() }) }

    return emptyList()
  }

  @Suppress("unused")
  internal class Provider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = TestProcessor()
  }
}
