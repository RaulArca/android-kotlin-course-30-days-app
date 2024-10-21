package com.example.mhw30days

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mhw30days.model.Monster
import com.example.mhw30days.model.MonsterRepository.monsterList
import com.example.mhw30days.ui.theme.MHW30DaysTheme

@Composable
@Preview
fun MonsterCardPreview(){
    val monster = Monster(
        descriptionRes = R.string.rathalos_desc,
        nameRes = R.string.rathalos_name,
        imageres = R.drawable.rathalos
    )
    MHW30DaysTheme {
            MonsterCard(monster,1)
    }
}

@Composable
fun MonsterCard(monster: Monster,day:Int, modifier: Modifier=Modifier){
    val dayStr = "Día "+day
    var extended by remember { mutableStateOf(false) }
    Card (modifier=modifier.clickable { extended=!extended }.animateContentSize(
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMediumLow
        )
    )){
        Column(Modifier.padding(horizontal =  dimensionResource(R.dimen.padding_medium), vertical = dimensionResource(R.dimen.padding_small))) {
            Text(text = dayStr, style = MaterialTheme.typography.labelLarge)
            Text(text = stringResource(monster.nameRes), style = MaterialTheme.typography.displayLarge)

            Image(painter = painterResource(monster.imageres),
                contentDescription = null ,
                contentScale = ContentScale.FillWidth,
                modifier =Modifier
                    .size(width = 370.dp, height = 185.dp)
                    .clip(MaterialTheme.shapes.small)
                )
            Spacer(Modifier.padding(4.dp))
            if(extended){

                Text(text = stringResource(monster.descriptionRes), style = MaterialTheme.typography.bodyLarge)
            }


        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MonsterListView(modifier: Modifier= Modifier, contentPaddingValues: PaddingValues= PaddingValues(0.dp)){
    val visibleState = remember{
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState= visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {LazyColumn(contentPadding =  contentPaddingValues) {
        itemsIndexed(monsterList){ index, monster ->
            MonsterCard(monster, index+1, modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = dimensionResource(R.dimen.padding_small)
            ).animateEnterExit(
                enter = slideInVertically(
                    animationSpec = spring(
                        stiffness = StiffnessVeryLow,
                        dampingRatio = DampingRatioLowBouncy
                    ),
                    initialOffsetY = {it * (index+1) }
                )
            ))

        }
    } }

}