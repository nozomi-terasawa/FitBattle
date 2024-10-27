package org.example.project.domain.utility

import java.security.MessageDigest

/**
 * String型をSHA256の文字列に変換する拡張関数
 */
fun String.hashWithSHA256(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}
