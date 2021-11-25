package com.aaron.helloalgorithm.algorithm

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
object Printer {

    fun print(
        title: CharSequence,
        input: CharSequence,
        output: CharSequence
    ) {
        println(
            """
            $title
            输入: $input
            输出: $output
            
        """.trimIndent()
        )
    }
}