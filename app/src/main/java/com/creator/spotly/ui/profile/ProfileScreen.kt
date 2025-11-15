package com.creator.spotly
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creator.spotly.data.dto.UserProfileData
import com.creator.spotly.ui.components.CustomIconButton
import com.creator.spotly.ui.components.CustomTopbar
import com.creator.spotly.ui.profile.ProfileViewModel
import com.creator.spotly.ui.profile.components.OptionRow
import com.creator.spotly.ui.theme.ButtonContainer
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {  },
    onEditClick: () -> Unit = {  },
    onLogoutClick: () -> Unit = {  },

    ) {
    val profile by profileViewModel.profile.collectAsState()
    ProfileContent(
        userProfile = profile,
        onBackClick = onBackClick,
        onEditClick = onEditClick,
        onLogoutClick = onLogoutClick
    )
}

@Composable
fun ProfileContent(
    userProfile: UserProfileData? = null,
    onBackClick: () -> Unit = {  },
    onEditClick: () -> Unit = {  },
    onLogoutClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        CustomTopbar(
            title = "Profile",
            backButtonHandler = onBackClick,
            item = {
                CustomIconButton(
                    onClick = onEditClick,
                    icon = Icons.Outlined.Edit,
                    contentColor = OrangeRed,

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
                painter = painterResource(id = R.drawable.user),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(userProfile?.name ?: "Unknown", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(userProfile?.email ?: "Unknown", color = Color.Gray, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF7F7F9), RoundedCornerShape(16.dp))
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Text("Rewards Points", fontSize = 14.sp, color = Color.Black)
                Text(userProfile?.rewardPoints.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6421))
            }
            Divider(
                color = Color(0xFFE0E0E0),
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Travel Trips", fontSize = 14.sp, color = Color.Black)
                Text(userProfile?.travelTrips.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6421))
            }
            Divider(
                color = Color(0xFFE0E0E0),
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Bucket List", fontSize = 14.sp, color = Color.Black)
                Text(userProfile?.bucketListItems.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6421))
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(Color(0xFFF7F7F9), RoundedCornerShape(16.dp))
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {

            OptionRow(
                icon = Icons.Default.BookmarkBorder,
                title = "Book Marks",
                onClick = {  }
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            OptionRow(
                icon = Icons.Default.Flight,
                title = "Previous Trips",
                onClick = {  }
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            OptionRow(
                icon = Icons.Default.Settings,
                title = "Settings",
                onClick = {  }
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            OptionRow(
                icon = Icons.Default.Info,
                title = "Version Info",
                onClick = {  }
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            OptionRow(
                icon = Icons.Default.Logout,
                title = "Logout",
                onClick = { onLogoutClick() }
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
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

    ProfileContent(
        userProfile = fakeUser,
        onBackClick = {},
        onEditClick = {},
        onLogoutClick = {}
    )
}