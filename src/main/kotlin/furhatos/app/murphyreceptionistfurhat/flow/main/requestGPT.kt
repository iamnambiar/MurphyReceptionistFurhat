package furhatos.app.murphyreceptionistfurhat.flow.main

import com.theokanning.openai.completion.CompletionRequest
import com.theokanning.openai.service.OpenAiService
import furhatos.flow.kotlin.DialogHistory
import furhatos.flow.kotlin.Furhat
import java.lang.Exception


fun getNLGResponseFromGPT3(textInput: String) : String {
    var key = readFile("C:\\Users\\neera\\api.key")
    val openAiApi = OpenAiService(key)
    // Read more about these settings: https://beta.openai.com/docs/introduction
    var temperature = 0.6 // Higher values means the model will take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
    var maxTokens =  75// Length of output generated. 1 token is on average ~4 characters or 0.75 words for English text
    var topP = 1.0 // 1.0 is default. An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
    var frequencyPenalty = 0.0 // Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
    var presencePenalty = 0.0 // Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.

    val history = Furhat.dialogHistory.all.takeLast(10).mapNotNull {
        when (it) {
            is DialogHistory.ResponseItem -> {
                "Human: ${it.response.text}"
            }
            is DialogHistory.UtteranceItem -> {
                "Murphy: ${it.toText()}"
            }
            else -> null
        }
    }.joinToString(separator = "\n")

    var promptInputText = "A receptionist called murphy at the national robotarium is talking to a human. " +
            "Generate a casual conversation response.Information given below should only be used.\n"+ textInput + "\n" +
            "The conversation history with human and Murphy is also provided below\n"+history+"\n" +
            "Use the above information to create an informal conversation that can be used by a social receptionist to say it as a response to a " +
            "user without greeting them or wishing within 50 words. "
    var response = ""

    val completionRequest = CompletionRequest.builder()
        .model("curie")
        .temperature(temperature)
        .topP(topP)
        .frequencyPenalty(frequencyPenalty)
        .presencePenalty(presencePenalty)
        .maxTokens(maxTokens)
        .prompt(promptInputText)
        .echo(true)
        .build()

    try {
        val completion = openAiApi.createCompletion(completionRequest).choices.first().text
        println("Completion Request Satisfied")
//        println(completion)
//        openAiApi.createCompletion(completionRequest).choices.forEach(System.out::println)
        response = completion.drop(promptInputText.length).trim()

    } catch (e: Exception) {
        println("Problem with connection to OpenAI" + e.message)
    }
    println("-------------------------------------------------")
    println("Response: \n$response")
    println("-------------------------------------------------")
    return response
}