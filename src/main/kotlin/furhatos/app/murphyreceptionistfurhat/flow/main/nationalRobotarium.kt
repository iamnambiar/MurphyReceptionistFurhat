package furhatos.app.murphyreceptionistfurhat.flow.main

import furhatos.app.murphyreceptionistfurhat.flow.Parent
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.app.murphyreceptionistfurhat.nlu.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val NationalRobotariumMainState : State = state(Parent) {
    onEntry {
        random(
            { furhat.ask("How may I assist you?") },
            { furhat.ask("What can I help you with today?") },
            { furhat.ask("How may I be of service to you today?") },
            { furhat.ask("How can I assist you during your visit?") },
            { furhat.ask("How can I help you today?") },
            { furhat.ask("How may I assist you on this fine day?") },
            { furhat.ask("How can I be of assistance?") },
            { furhat.ask("how can I assist you today? Is there anything you need help with?") }
        )
    }
    onReentry {
        furhat.ask("Is there something specific you're interested in learning more about the National Robotarium?")
    }
    include(NationalRobotariumResponses)
    onResponse<No> {
        goto(GoToIdleState)
    }
}

val CheckIfUserVisitedState : State = state(Parent) {
    onEntry {
        furhat.ask("Have you been to the National Robotarium before?")
    }
    onResponse<Yes> {
        furhat.say("Oh. That is nice. It's good to have you back.")
        goto(NationalRobotariumMainState)
    }
    onResponse<No> {
        furhat.say("That's Ok.")
        goto(NationalRobotariumTour)
    }
}

var NationalRobotariumTour : State = state(Parent) {
    onEntry {
        furhat.ask("Are you interested in a tour of the building.")
    }
    include(UniversalResponses)
    onResponse<Yes> {
        call(AboutNR)
    }
    onResponse<No> {
        goto(NationalRobotariumMainState)
    }
}

var NationalRobotariumResponses = partialState {
    include(UniversalResponses)
    onResponse<HistoryIntent> {
        call(AboutNR)
        furhat.say("That's all regarding the building.")
        reentry()
    }
}

val NoResponseState : State = state(Parent) {
    onEntry {
        furhat.ask("Are you interested in learning more about history of the National Robotarium?")
        onResponse<Yes> {
            goto(AboutNR)
        }
        onResponse<No> {
            goto(GoToIdleState)
        }
//        onResponse {
//            goto(IntroductionState)
//        }
    }
}
