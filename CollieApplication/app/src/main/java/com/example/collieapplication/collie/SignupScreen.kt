import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SignupScreen(
    modifier: Modifier = Modifier
    , str: String
    , onValueChange: (String) -> Unit
    , onCompleteButtonClicked: () -> Unit
) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = str
                , onValueChange = onValueChange
                , modifier = Modifier.padding(vertical = 24.dp)
            )
            Button(onClick = onCompleteButtonClicked) { // TODO api 호출을 통해 id 저장하는 로직 추가
                Text(text = "submit")
            }
        }
    }
    
}

@Preview
@Composable
private fun SignupScreenPreview() {
    var signUpValue by remember { // 이렇게 모든 화면에서 필요한 값들을 이렇게 선언해두고 써야되는건가..?
        mutableStateOf("")
    }
    SignupScreen(str = signUpValue, onValueChange = {signUpValue = it}
        , onCompleteButtonClicked = {})
}