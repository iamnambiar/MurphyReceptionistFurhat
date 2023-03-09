package furhatos.app.murphyreceptionistfurhat.flow.main

import furhatos.app.murphyreceptionistfurhat.flow.Parent
import furhatos.app.murphyreceptionistfurhat.nlu.AboutMeIntent
import furhatos.flow.kotlin.*
import furhatos.nlu.common.AskName
import furhatos.nlu.common.No
import furhatos.nlu.common.RequestRepeat
import furhatos.nlu.common.Yes

val Greeting : State = state(Parent) {
    onEntry {
        dialogLogger.startSession()
        random(
            { furhat.say("Hi there!") },
            { furhat.say("Hello there!") }
        )
        furhat.say("Welcome to the National Robotarium!")
        furhat.say("I am Murphy, the robot receptionist here.")
        call(whatCanIDo(false))
        furhat.say("It's good to meet you!")
        goto(CheckIfUserVisitedState)
    }
    include(UniversalResponses)
}

var UniversalResponses = partialState {
    onResponse<AskName> {
        furhat.say("My name is Murphy. I am a receptionist robot here " +
                "at the National Robotarium")
        reentry()
    }
    onResponse<RequestRepeat> {
        reentry()
    }
    onResponse<AboutMeIntent> {
        call(whatCanIDo(true))
        reentry()
    }
}

fun whatCanIDo(firstTime: Boolean = false) : State = state {
    onEntry {
        if (firstTime) {
            furhat.say("As a receptionist, ")
            random(
                { furhat.say("I'm happy to provide you with information about the building and current research and news on robotics.") },
                { furhat.say("I'm here to give you a detailed overview of the building and the advancements in robotics happening here.") },
                { furhat.say("I'm happy to talk about the building and the latest developments in robotics that are taking place here.") },
                { furhat.say("I'm available to discuss the building, share exciting updates, and chat about robotics.") }
            )
        }
        else {
            random(
                {
                    furhat.say(
                        "I can tell you all about the building, " +
                                "share few exciting news and researches happening here, " +
                                "and even give you a general talk about robotics."
                    )
                },
                {
                    furhat.say(
                        "I can provide you with information about " +
                                "the facilities at National Robotarium, updates on exciting " +
                                "research projects taking place here, and " +
                                "even offer a brief overview of robotics in general."
                    )
                }
            )
        }

        terminate()
    }
}

fun sayName() : State = state {
    onEntry {
        furhat.say("I am Charlie. I am the receptionist here at the National Robotarium.")
        terminate()
    }
}

val GoToIdleState : State = state(Parent) {
    onEntry {
        furhat.say("Let me know if you have any other questions, " +
                "or if there's anything else I can assist you with.")
        dialogLogger.endSession()
        goto(Idle)
    }
}

