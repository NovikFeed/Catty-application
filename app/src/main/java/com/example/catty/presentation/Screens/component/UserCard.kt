package com.example.catty.presentation.Screens.component

import android.media.ImageReader
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.catty.R
import com.example.catty.personList.domain.model.User

@Composable
fun UserCard(
    user: User
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(user.photo)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Box() {
        Row {
            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    painter = imageState.painter,
                    contentDescription = "photo",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "photo",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    modifier = Modifier.width(270.dp),
                    text = user.name,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.nunito_sans)
                    ),
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = user.position,
                    fontSize = 14.sp,
                    color = Color(0, 0, 0, 60),
                    fontFamily = FontFamily(
                        Font(R.font.nunito_sans)
                    )
                )
                Text(
                    text = if (user.email.length <= 32) user.email else user.email.substring(
                        0,
                        40
                    ) + "...",
                    fontSize = 14.sp, fontFamily = FontFamily(
                        Font(R.font.nunito_sans)
                    )
                )
                Text(
                    text = user.phone, fontSize = 14.sp, fontFamily = FontFamily(
                        Font(R.font.nunito_sans)
                    )
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}