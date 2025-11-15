package com.creator.spotly.ui.profile

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.creator.spotly.R
import com.creator.spotly.data.dto.UserProfileData
import com.creator.spotly.ui.components.CustomIconButton
import com.creator.spotly.ui.components.CustomTextField
import com.creator.spotly.ui.components.CustomTopbar
import com.creator.spotly.ui.profile.components.CustomField
import com.creator.spotly.ui.profile.components.OptionRow
import com.creator.spotly.ui.state.UiState
import com.creator.spotly.ui.theme.ButtonContainer
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.DeepOrange
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    editViewModel: EditProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {  },
    onDoneClick: () -> Unit = {  },
    ) {
    val context = LocalContext.current
    val state by editViewModel.uiState.collectAsState()
    EditProfileContent(
        name = state.name,
        onBackClick = onBackClick,
        onDoneClick = {
            editViewModel.saveProfile()
            if (state.error != null) {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                onDoneClick()
            }
        },
        onNameChange = editViewModel::onNameChange,
        onChangeProfilePictureClick = {}
    )
}

@Composable
fun EditProfileContent(
    name: String? = null,
    onBackClick: () -> Unit = {  },
    onDoneClick: () -> Unit = {  },
    onNameChange: (String) -> Unit = {  },
    onChangeProfilePictureClick: () -> Unit = {  }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        CustomTopbar(
            title = "Edit Profile",
            backButtonHandler = onBackClick,
            item = {
                Text(
                    text = "Done",
                    color = OrangeRed,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = CustomFontFamily,
                    modifier = Modifier
                        .clickable {
                            onDoneClick()
                        }
                )
            },
            backContainerColor = ButtonContainer
        )

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFDFE6))
            )

        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                name ?: "Unknown",
                fontSize = 24.sp,
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium
            )
            Text(
                "Change Profile Picture",
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium,
                color = DeepOrange,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    onChangeProfilePictureClick()
                }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        CustomField(
            title = "Name",
            value = name ?: "",
            onValueChange = onNameChange
        )


    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val fakeUser = UserProfileData(
        uid = "123",
        name = "Reyna Mohamed",
        email = "Ellaithy@email.com",
        avatar = null,
        rewardPoints = 50,
        travelTrips = 40,
        bucketListItems = 200
    )

    EditProfileContent(
        name = fakeUser.name,
        onBackClick = {},
        onDoneClick = {},
    )
}