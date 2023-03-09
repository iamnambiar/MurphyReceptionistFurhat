package furhatos.app.murphyreceptionistfurhat.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.WildcardEntity
import furhatos.util.Language

class PositiveExpressionEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "great",
            "fine:fine,define",
            "happy",
            "nice",
            "fantastic",
            "awesome",
            "good:good,best",
            "amazing",
            "incredible",
            "excellent",
            "perfect",
            "fabulous",
            "hilarious",
            "epic",
            "love",
            "very well",
            "really well",
            "excited",
            "astonished",
            "cool",
            "terrific",
            "cheerful",
            "contented",
            "delighted",
            "ecstatic",
            "elated",
            "glad",
            "joyful",
            "joyous",
            "jubilant",
            "lively",
            "merry",
            "overjoyed",
            "peaceful",
            "pleasant",
            "pleased",
            "thrilled",
            "upbeat",
            "blessed",
            "blest",
            "blissful",
            "blithe",
            "can't complain",
            "captivated",
            "chipper",
            "chirpy",
            "content",
            "convivial",
            "exultant",
            "gay",
            "gleeful",
            "gratified",
            "intoxicated",
            "jolly",
            "laughing",
            "light",
            "looking good",
            "mirthful",
            "on cloud nine",
            "peppy",
            "perky",
            "playful",
            "sparkling",
            "sunny",
            "tickled",
            "up",
            "alright:alright, all right",
            "splendid",
            "good-looking"
        )
    }
}
class NegativeExpressionEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "terrible:terrible,parable",
            "bad:bad,dad,worst",
            "horrible",
            "awful",
            "dreadful",
            "atrocious",
            "appalling",
            "shit",
            "sucks",
            "crap",
            "annoying",
            "unacceptable",
            "unprofessional",
            "too shabby",
            "flawed:flawed,flaws"
        )
    }
}

class InterestingEmotionEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "suprised:surprised,surprise",
            "scared",
            "interested",
            "nervous",
            "afraid",
            "stressed",
            "stunned",
            "startled"
        )
    }
}

class NervousEmotionEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "suprised:surprised,surprise",
            "scared",
            "interested",
            "nervous",
            "afraid",
            "stressed",
            "stunned",
            "startled",
            "strange",
            "weird",
            "odd",
            "pressure"
        )
    }
}

class QualifierEntity : WildcardEntity("message", PositiveReactionIntent())

class PositiveReactionIntent(
    val positiveExpressionEntity: PositiveExpressionEntity? = null,
    val negativeExpressionEntity: NegativeExpressionEntity? = null,
    val interestingEmotionEntity: InterestingEmotionEntity? = null,
    var qualifier: QualifierEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "@positiveExpressionEntity",
            "not @negativeExpressionEntity",
            "not too @negativeExpressionEntity",
            "@interestingEmotionEntity"
        )
    }

    override fun getNegativeExamples(lang: Language): List<String> {
        return listOf(
            "not @positiveExpressionEntity",
            "@negativeExpressionEntity",
            "not @interestingEmotionEntity"
        )
    }
}

class NegativeReactionIntent(
    val positiveExpressionEntity: PositiveExpressionEntity? = null,
    val negativeExpressionEntity: NegativeExpressionEntity? = null,
    val interestingEmotionEntity: InterestingEmotionEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "not @positiveExpressionEntity",
            "@negativeExpressionEntity",
            "not @interestingEmotionEntity"
        )
    }

    override fun getNegativeExamples(lang: Language): List<String> {
        return listOf(
            "@positiveExpressionEntity",
            "not @negativeExpressionEntity",
            "not too @negativeExpressionEntity",
            "@interestingEmotionEntity"
        )
    }
}


class InterestingReactionIntent(
    val positiveExpressionEntity: PositiveExpressionEntity? = null,
    val negativeExpressionEntity: NegativeExpressionEntity? = null,
    val interestingEmotionEntity: NervousEmotionEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "not @positiveExpressionEntity",
            "@negativeExpressionEntity",
            "@interestingEmotionEntity"
        )
    }
}