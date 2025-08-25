package chapter_05.java_sam.sam

import chapter_05.java_sam.lambda.OnClickListener

fun main() {
    addListener {
        // do something
//        removeListener(this) // 람다는 자기자신을 가리킬 수 없어서 this 불가능
    }

    addListener(object : OnClickListener {
        override fun onClick() {
            // do something
            removeListener(this) // 무명 객체의 경우, this 가능
        }
    })
}

fun addListener(clickListener: OnClickListener) {
    // do something
}

fun removeListener(clickListener: OnClickListener) {
    // do something
}