package com.example.collieapplication.collie

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.collieapplication.collie.model.ledger.LedgerViewModel

// comp
// method vs 함수
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    ledgerList: List<LedgerViewModel.Ledger>
) {

    Surface(modifier
        , color = MaterialTheme.colorScheme.background) {

        LazyColumn(
            modifier = modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()
        ) {
            items(items = ledgerList) {
                LedgerCardList(ledger = it)
            }
        }
    }

}

@Composable
fun LedgerCardList(ledger: LedgerViewModel.Ledger, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        LedgerCard(ledger)
    }
}

@Composable
fun LedgerCard(ledger: LedgerViewModel.Ledger) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = ledger.transactionTime.toString()
            )
            Text(
                text = ledger.amount.toString()
                , style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        Button(
            modifier = Modifier
                //.weight(1f)
                //.padding(12.dp)
                //.border(width = 2.dp, color = Color.Black)
            ,
            onClick = { /*TODO*/ }) {
            Text(text = "수정")
        }
        Button(
            modifier = Modifier
                //.border(width = 2.dp, color = Color.Black)
            , onClick = { /*TODO*/ }) {
            Text(text = "삭제")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(ledgerList = LedgerViewModel().ledgerList)
}