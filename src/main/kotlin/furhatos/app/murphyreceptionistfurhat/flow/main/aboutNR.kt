package furhatos.app.murphyreceptionistfurhat.flow.main

import furhatos.app.murphyreceptionistfurhat.flow.Parent
import furhatos.app.murphyreceptionistfurhat.nlu.PositiveReactionIntent
import furhatos.flow.kotlin.*
import furhatos.nlu.common.AskName
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.No
import furhatos.nlu.common.Number

val AboutNR : State = state(Parent) {
    onEntry {
        furhat.say("Great! I'm glad to hear you're interested in learning about the building.")
        furhat.say("We have a rich history here at the National Robotarium and I'm excited to share it with you.")
        furhat.say("The National Robotarium is a state-of-the-art research facility located in Edinburgh. " +
                "It was officially opened on September 2022, following a collaboration between the University of Edinburgh and Heriot-Watt, along with the support from the Government.")
        furhat.ask("Do you know how much funding was awarded to the project from the UK government?")
    }
    onReentry {
        furhat.say("I will repeat my question again!")
        furhat.ask("Do you know how much funding was awarded for this building from the UK government?")
    }
    include(UniversalResponses)
    onResponse<DontKnow> {
        call(dontKnowFunding())
        terminate()
    }
    onResponse<No> {
        call(dontKnowFunding())
        terminate()
    }
}

fun dontKnowFunding() : State = state {
    onEntry {
        furhat.say("The project was awarded 21 million pounds in funding from the UK government’s Industrial Strategy Challenge Fund with an additional £1.4 million from the Scottish Funding Council.")
        furhat.ask("What do you think?")
    }
    onResponse<PositiveReactionIntent> {
        var commentFromUser = it.intent.positiveExpressionEntity
        if (commentFromUser != null) {
            var word = if (commentFromUser.value != null) commentFromUser.value.toString() else commentFromUser.toText()
            furhat.say("Yeah. Since its opening, the National Robotarium has become a hub for robotics research and innovation, bringing together" +
                    "leading researchers, engineers, and industry partners to work on cutting-edge projects.")
            terminate()
        }

    }
    onResponse {
        furhat.say("Since its opening, the National Robotarium has become a hub for robotics research and innovation, bringing together " +
                "leading researchers, engineers, and industry partners to work on cutting-edge projects.")
        terminate()
    }
}