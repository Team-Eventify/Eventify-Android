package com.example.eventify.data.remote.utils

import okhttp3.Response


val Response.ok: Boolean
    get() = this.code == 200
