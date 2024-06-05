package com.example.pokemon.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("abilities")
    var abilities: ArrayList<Abilities> = arrayListOf(),
    @SerializedName("base_experience")
    var baseExperience: Int? = null,
    @SerializedName("cries")
    var cries: Cries? = null,
    @SerializedName("forms")
    var forms: ArrayList<Forms> = arrayListOf(),
    @SerializedName("game_indices")
    var gameIndices: ArrayList<GameIndices> = arrayListOf(),
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("held_items")
    var heldItems: ArrayList<Any> = arrayListOf(),
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("is_default")
    var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters")
    var locationAreaEncounters: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("order")
    var order: Int? = null,
    @SerializedName("past_abilities")
    var pastAbilities: ArrayList<String> = arrayListOf(),
    @SerializedName("past_types")
    var pastTypes: ArrayList<String> = arrayListOf(),
    @SerializedName("species")
    var species: Species? = null,
    @SerializedName("stats")
    var stats: ArrayList<Stats> = arrayListOf(),
    @SerializedName("sprites")
    var sprites: Sprites? = null,
    @SerializedName("types")
    var types: ArrayList<Types> = arrayListOf(),
    @SerializedName("weight") var weight: Int? = null
)

data class Ability(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class Abilities(
    @SerializedName("ability") var ability: Ability? = Ability(),
    @SerializedName("is_hidden") var isHidden: Boolean? = null,
    @SerializedName("slot") var slot: Int? = null
)

data class Cries(
    @SerializedName("latest") var latest: String? = null,
    @SerializedName("legacy") var legacy: String? = null
)

data class Forms(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class Version(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class GameIndices(
    @SerializedName("game_index") var gameIndex: Int? = null,
    @SerializedName("version") var version: Version? = Version()
)

data class Species(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class OfficialArtwork(
    @SerializedName("front_default") var frontDefault: String? = null,
)

data class Other(
    @SerializedName("official-artwork") var officialArtwork: OfficialArtwork? = null,
)

data class Sprites(
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_female") var backFemale: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null,
    @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
    @SerializedName("other") var other: Other? = null
)

data class Type(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class Types(
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: Type? = Type()
)

data class Stats(
    @SerializedName("base_stat") var baseStat: Int? = null,
    @SerializedName("effort") var effort: Int? = null,
    @SerializedName("stat") var stat: Stat? = Stat()
)

data class Stat(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
