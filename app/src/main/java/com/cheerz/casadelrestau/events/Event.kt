package com.cheerz.casadelrestau.events

interface Event {
    interface CreateEventDisplayer {
        fun showCreateEvent(name: String, id: Int)
    }
}
