package tech.dagimtesfaye.cbe_balance_tracker.view.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tech.dagimtesfaye.cbe_balance_tracker.R
import tech.dagimtesfaye.cbe_balance_tracker.data.model.OnboardingItem
import tech.dagimtesfaye.cbe_balance_tracker.navigation.Screen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    imageCornerRadius: Dp = 16.dp,
    imageHeight: Dp = 250.dp,
) {
    val onboardingItems = listOf(
        OnboardingItem(
            R.drawable.office_management_amico,
            R.string.onboarding_title_cbe_account,
            R.string.onboarding_body_cbe_account
        ),
        OnboardingItem(
            R.drawable.finance_app_amico__1_,
            R.string.onboarding_title_transaction_history,
            R.string.onboarding_body_transaction_history
        ),
        OnboardingItem(
            R.drawable.dark_analytics_amico,
            R.string.onboarding_title_analytics,
            R.string.onboarding_body_analytics
        )
    )
    val pagerState = rememberPagerState(pageCount = { 3 })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize(),
            ) { page ->
                val item = onboardingItems.getOrNull(page)
                if (item != null) {
                    // Display the content of the onboarding item
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Display the image
                        Image(
                            painter = painterResource(id = item.imageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(imageHeight)
                                .clip(RoundedCornerShape(imageCornerRadius))
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Display the title
                        Text(
                            text = stringResource(id = item.titleResId),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Display the body text
                        Text(
                            text = stringResource(id = item.bodyResId),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Dot indicators for navigation
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(onboardingItems.size) { index ->
                    val color =
                        if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else Color.LightGray
                    val width = if (pagerState.currentPage == index) 50.dp else 25.dp
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .height(7.dp)
                            .width(width)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (pagerState.currentPage == 2) {
                    // This button only appears on the last item of the onboarding screen
                    Button(
                        onClick = {
                            navController.navigate(Screen.ProfileSetupScreen.route){
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.height(45.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = "Set Up Profile",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 15.sp
                            ),
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Next Button Icon",
                            tint = (MaterialTheme.colorScheme.primary),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }
        }


    }
}
