package com.creator.spotly.domain.model

data class Message(
    val text: String,
    val time: String,
    val isFromUser: Boolean,
    val showAvatar: Boolean = false,
    val isSeen: Boolean = false
)