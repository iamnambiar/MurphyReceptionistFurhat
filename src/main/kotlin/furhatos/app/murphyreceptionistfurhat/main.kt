package furhatos.app.murphyreceptionistfurhat

import furhatos.app.murphyreceptionistfurhat.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class MurphyreceptionistfurhatSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
