package com.example.collieapplication


//    val extraPadding by animateDpAsState (
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        ), label = "size"
//    )

//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(24.dp)
//        ) {
////            Column(modifier = modifier
////                .fillMaxWidth()
////                .padding(24.dp)) {
////                Text(text = "hello ")
////                Text(text = name)
////            }
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "hello ")
//                Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(
//                    fontWeight = FontWeight.ExtraBold
//                ))
//            }
//            ElevatedButton(onClick = { expanded = !expanded}) {
//                Text(if (expanded) "Show less" else "Show more")
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, widthDp = 320)
//@Composable
//private fun GreetingPreview() {
//    //MainScreen()
//    MyApplicationTheme {
//        Greetings()
//    }
//
//    // https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#0
//    // ㅎㅐ보기
//    // 다음시간에 스웨거 붙이기
//}