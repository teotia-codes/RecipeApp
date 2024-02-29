package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.ui.theme.Category

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel :MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    NavHost(navController = navController,
        startDestination = Screen.RecipeScreen.route ){
     composable(route = Screen.RecipeScreen.route){
         RecipeScreen(navigateToDetail ={
             //This part is responsible for passing data from the current screen to detail screen.
             // It utilizes the savedStateHandle, which is a component of the Compose navigation framework.
             //It sores the data as it is passed from one screen to another
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
                                        } ,
             viewstate = viewstate)
     }
        composable(route= Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.
            savedStateHandle?.get<Category>("cat")?:Category("",
                "",
                "",
                "")
            CategoryDetailScreen(category = category)
        }
    }

}