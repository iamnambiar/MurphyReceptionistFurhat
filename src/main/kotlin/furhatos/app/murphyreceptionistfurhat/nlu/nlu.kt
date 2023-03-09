package furhatos.app.murphyreceptionistfurhat.nlu

import furhatos.nlu.Intent
import furhatos.nlu.EnumEntity
import furhatos.util.Language


class GreetingIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Hello",
            "Hi",
            "Hello there",
            "Hi there",
            "Good Morning",
            "Good Afternoon",
            "Good Evening",
            "Nice to meet you"
        )
    }
}

class AboutMeIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Who are you?",
            "What can you do?",
            "Why are you here?",
            "Can you talk about yourself?",
            "What is your purpose?",
            "Talk about you!",
            "Explain yourself"
        )
    }
}


class HistoryIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "what is National robotarium?",
            "what is this building?",
            "can you talk about this building?",
            "can you talk about this robotarium?",
            "can you talk about this National robotarium?",
            "Explain the brief history of this building",
            "Explain the brief history of National robotarium",
            "Explain the brief history of robotarium",
            "What do you know about this building?",
            "What do you know about National robotarium?",
            "What do you know about robotarium?",
            "Talk about this building",
            "Tell me about the National Robotarium",
            "Tell me about the building"
        )
    }
}

class LatestResearchIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "What are the latest research",
            "What are the latest research happening here"
        )
    }
}