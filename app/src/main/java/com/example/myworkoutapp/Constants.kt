package com.example.myworkoutapp

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_blankwhite,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val sidePlank = ExerciseModel(
            2,
            "Side Plank",
            R.drawable.ic_blankwhite,
            false,
            false
        )
        exerciseList.add(sidePlank)

        val wallSit = ExerciseModel(
            3,
            "Wall Sit",
            R.drawable.ic_blankwhite,
            false,
            false
        )
        exerciseList.add(wallSit)

        val pushUp = ExerciseModel(
            4,
                "Push Up",
            R.drawable.ic_blankwhite,
            false,
            false
        )
        exerciseList.add(pushUp)

        val abdominalCrunch = ExerciseModel(
            5,
            "Abdominal Crunch",
            R.drawable.ic_blankwhite,
            false,
            false
        )
        exerciseList.add(abdominalCrunch)


        return exerciseList
    }

}