package com.example.a30_days_fitness_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30_days_fitness_app.ui.theme._30_Days_Fitness_AppTheme


data class Exercise(
    val day : String,
    val name: String,
    val imageResId: Int,
    val details: String
)

private val exerciseList = listOf(
    Exercise(
        day = "Day 1",
        name = "Push-ups: Targeting chest and arms",
        imageResId = R.drawable.pushups,
        details = "A classic upper body exercise that targets the chest, shoulders, and triceps. 3 sets of 10 reps"
    ),
    Exercise(
        day = "Day 2",
        name = "Squats: Targeting legs and glutes",
        imageResId = R.drawable.squats,
        details = "Targets the lower body muscles, including quadriceps, hamstrings, and glutes. 4 sets of 12 reps"
    ),
    Exercise(
        day = "Day 3",
        name = "Lunges: Improve Balance",
        imageResId = R.drawable.lunges,
        details = "Works the legs and improves balance and coordination.Alternate legs and do 3 sets of 12 reps per leg"
    ),
    Exercise(
        day = "Day 4",
        name = "Planks:  Core-strengthening",
        imageResId = R.drawable.planks,
        details = "Exercise that also engages the shoulders, arms, and back.\n" +
                "Hold for 30 seconds to 1 minute, increasing time as you build strength"
    ),
    Exercise(
        day = "Day 5",
        name = " Burpees:  Full-body exercise",
        imageResId = R.drawable.burpees,
        details = "combining squats, push-ups, and jumps for cardiovascular and strength benefits.\n" +
                "Do 3 sets of 10 reps, increasing intensity as you get stronger.\n"
    ),
    Exercise(
        day = "Day 6",
        name = "Mountain Climbers: Cardiovascular and core",
        imageResId = R.drawable.mountain_climbers,
        details = "Exercise that also engages the arms and legs.\n" +
                "Do 4 sets of 20 reps (10 per leg), maintaining a steady pace."
    ),
    Exercise(
        day = "Day 7",
        name = "Jumping Jacks: Cardiovascular",
        imageResId = R.drawable.jumping_jacks,
        details = "Exercise that also works the arms, legs, and core.\n" +
                "Start with 3 sets of 30 seconds, increasing duration as you improve."
    ),
    Exercise(
        day = "Day 8",
        name = "High Knees: Cardiovascular and lower body",
        imageResId = R.drawable.high_knees,
        details = "Exercise that also improves coordination.\n" +
                "Do 3 sets of 30 seconds, aiming for high knees and quick pace."
    ),
    Exercise(
        day = "Day 9",
        name = "Bicycle Crunches: Abdominal muscles",
        imageResId = R.drawable.bicycle_crunches,
        details = "Targets the abdominal muscles and obliques for core strength.\n" +
                "Perform 3 sets of 15 reps per side, focusing on twisting motion."
    ),
    Exercise(
        day = "Day 10",
        name = "Leg Raises: Abdominal muscles and hip flexor",
        imageResId = R.drawable.leg_raises,
        details = "Strengthens the lower abdominal muscles and hip flexors.\n" +
                "Do 4 sets of 12 reps, keeping legs straight and lowering them slowly."
    ),
    Exercise(
        day = "Day 11",
        name = "Plank with Leg Lifts: Core strength and stability",
        imageResId = R.drawable.plank_with_leg_lifts,
        details = "Builds core strength and stability while targeting the glutes and lower back.\n" +
                "Hold a plank position and lift each leg for 10 reps, alternating sides."
    ),
    Exercise(
        day = "Day 12",
        name = "Dumbbell Rows: Upper back, shoulders, and arms",
        imageResId = R.drawable.dumbbell_rows,
        details = "Strengthens the upper back, shoulders, and arms.\n" +
                "Do 3 sets of 12 reps per arm, using a moderate weight dumbbell."
    ),
    Exercise(
        day = "Day 13",
        name = "Tricep Dips: Triceps and shoulderss",
        imageResId = R.drawable.tricep_dips,
        details = "Targets the triceps and shoulders for upper body strength.\n" +
                "Perform 3 sets of 12 reps using a bench or stable surface."
    ),
    Exercise(
        day = "Day 14",
        name = "Russian Twists: Obliques and core muscles",
        imageResId = R.drawable.russian_twists,
        details = "Works the obliques and core muscles for rotational strength.\n" +
                "Do 3 sets of 20 reps (10 per side), holding a weight or medicine ball."
    ),
    Exercise(
        day = "Day 15",
        name = "Side Planks: Obliques and core stability.",
        imageResId = R.drawable.side_planks,
        details = "Targets the obliques and improves core stability.\n" +
                "Hold each side plank for 30 seconds to 1 minute, switching sides."
    ),
    Exercise(
        day = "Day 16",
        name = "Jump Squats: Explosive lower body",
        imageResId = R.drawable.jump_squats,
        details = "Explosive lower body exercise for power and agility.\n" +
                "Do 3 sets of 10 reps, jumping as high as possible from a squat position."
    ),
    Exercise(
        day = "Day 17",
        name = "Push-up with Rotation: Core and shoulders",
        imageResId = R.drawable.pushup_with_rotation,
        details = "Combines push-ups with a rotational movement to engage the core and shoulders.\n" +
                "Perform 3 sets of 10 reps, rotating to each side after each push-up."
    ),
    Exercise(
        day = "Day 18",
        name = "Wall Sit: Leg strength and endurances",
        imageResId = R.drawable.wall_sit,
        details = "Builds leg strength and endurance with an isometric hold.\n" +
                "Hold the wall sit for 1 minute, increasing time as you progress."
    ),
    Exercise(
        day = "Day 19",
        name = "Calf Raises: Calf muscles",
        imageResId = R.drawable.calf_raises,
        details = "Targets the calf muscles for lower leg strength.\n" +
                "Do 4 sets of 15 reps, using a step or ledge for added range of motion."
    ),
    Exercise(
        day = "Day 20",
        name = "Superman: Glutes and shoulders",
        imageResId = R.drawable.superman,
        details = "Strengthens the lower back and engages the glutes and shoulders.\n" +
                "Hold the Superman position for 30 seconds to 1 minute, focusing on lifting arms and legs."
    ),
    Exercise(
        day = "Day 21",
        name = "Bridge: Hamstrings, and lower back",
        imageResId = R.drawable.bridge,
        details = "Works the glutes, hamstrings, and lower back for posterior chain strength.\n" +
                "Hold the bridge position for 30 seconds to 1 minute, squeezing glutes at the top."
    ),
    Exercise(
        day = "Day 22",
        name = "Plank Jacks: Core and cardiovascular",
        imageResId = R.drawable.plank_jacks,
        details = "Combines plank position with jumping jacks for core and cardiovascular benefits.\n" +
                "Do 3 sets of 20 reps, maintaining a steady pace."
    ),
    Exercise(
        day = "Day 23",
        name = "Lateral Lunges: Inner and outer thighs",
        imageResId = R.drawable.lateral_lunges,
        details = "Targets the inner and outer thighs, glutes, and hamstrings.\n" +
                "Do 3 sets of 12 reps per leg, stepping out to the side and bending the knee."
    ),
    Exercise(
        day = "Day 24",
        name = "Inchworms: Hamstrings and strengthens",
        imageResId = R.drawable.inchworms,
        details = "Dynamic exercise that stretches the hamstrings and strengthens the core.\n" +
                "Perform 3 sets of 10 reps, walking hands out to plank position and back."
    ),
    Exercise(
        day = "Day 25",
        name = "Reverse Crunches: Abdominal muscles and core strength",
        imageResId = R.drawable.reverse_crunches,
        details = "Focuses on lower abdominal muscles for core strength.\n" +
                "Do 3 sets of 15 reps, lifting hips off the ground towards the chest."
    ),
    Exercise(
        day = "Day 26",
        name = "Shoulder Press: Shoulder muscles",
        imageResId = R.drawable.shoulder_press,
        details = "Strengthens the shoulder muscles for upper body strength.\n" +
                "Do 3 sets of 12 reps, using dumbbells or a barbell."
    ),
    Exercise(
        day = "Day 27",
        name = "Bicep Curls: Bicep",
        imageResId = R.drawable.bicep_curls,
        details = "Targets the biceps for arm strength and definition.\n" +
                "Perform 3 sets of 12 reps, using dumbbells or a resistance band."
    ),
    Exercise(
        day = "Day 28",
        name = "Leg Press: Quadriceps, hamstrings, and glutess",
        imageResId = R.drawable.leg_press,
        details = "Works the quadriceps, hamstrings, and glutes with a leg press machine.\n" +
                "Do 3 sets of 12 reps, adjusting weight as needed for challenge."
    ),
    Exercise(
        day = "Day 29",
        name = "Hamstring Curls: Hamstring",
        imageResId = R.drawable.hamstring_curls,
        details = "Targets the hamstrings for lower body strength and stability.\n" +
                "Perform 3 sets of 12 reps, using a leg curl machine or resistance band."
    ),
    Exercise(
        day = "Day 30",
        name = "Cool-down and Stretching",
        imageResId = R.drawable.cooldown_and_stretching,
        details = "Finish with a 10-minute cool-down, including stretching for all major muscle groups to improve flexibility and reduce muscle soreness."
    ),
    // Add more exercises here


)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30_Days_Fitness_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "30 Days Fitness App",
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        ExerciseList(exercises = exerciseList)
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseList(exercises: List<Exercise>) {
    val colorScheme = MaterialTheme.colorScheme

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
    ) {
        items(exercises) { exercise ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = colorScheme.surface),
                    shape = MaterialTheme.shapes.medium
            ) {
                ExerciseItem(exercise = exercise)
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = tween(durationMillis = 300)
            )
    ) {
        Text(text = exercise.day, style = MaterialTheme.typography.displaySmall)
        Text(text = exercise.name, style = MaterialTheme.typography.displayMedium)
        if (expanded) {
            Image(
                painter = painterResource(id = exercise.imageResId),
                contentDescription = exercise.name,
                modifier = Modifier.size(250.dp)
            )
            Text(text = exercise.details, style = MaterialTheme.typography.displaySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseListPreview() {
    _30_Days_Fitness_AppTheme {
        ExerciseList(exercises = exerciseList)
    }
}


