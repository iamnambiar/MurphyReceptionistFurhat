package furhatos.app.murphyreceptionistfurhat.flow

import furhatos.app.murphyreceptionistfurhat.flow.main.Idle
import furhatos.app.murphyreceptionistfurhat.setting.distanceToEngage
import furhatos.app.murphyreceptionistfurhat.setting.maxNumberOfUsers
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice

val Init : State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
        furhat.voice = Voice("Matthew")
        /** start the interaction */
        goto(Idle)
    }
}
