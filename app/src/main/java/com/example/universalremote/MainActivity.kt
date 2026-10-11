
package com.example.universalremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF090C11)
private val Panel = Color(0xFF171C25)
private val PanelLight = Color(0xFF222936)
private val Blue = Color(0xFF5487FF)
private val White = Color(0xFFF4F6FC)
private val Muted = Color(0xFF969FB0)
private val Red = Color(0xFFFF4D55)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RemoteScreen()
            }
        }
    }
}

@Composable
private fun RemoteScreen() {
    var selectedTv by remember { mutableStateOf("Select your TV") }
    var message by remember { mutableStateOf("Ready to connect") }
    var muted by remember { mutableStateOf(false) }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF151B26), Background, Background)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "UNIVERSAL REMOTE",
                        color = Blue,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "Your TV, simplified.",
                        color = White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                CircleButton(
                    symbol = "⚙",
                    size = 46,
                    onClick = { message = "Settings coming soon" }
                )
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Panel)
                    .border(1.dp, Color(0xFF303847), RoundedCornerShape(20.dp))
                    .clickable {
                        message = "TV setup will be added next"
                    }
                    .padding(17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(PanelLight),
                    contentAlignment = Alignment.Center
                ) {
                    Text("▣", color = Blue, fontSize = 26.sp)
                }

                Spacer(Modifier.width(13.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        selectedTv,
                        color = White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        "Tap to set up a television",
                        color = Muted,
                        fontSize = 11.sp
                    )
                }

                Text("›", color = Muted, fontSize = 27.sp)
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = message,
                color = Muted,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleControl(
                    symbol = if (muted) "♪" else "×",
                    label = if (muted) "Unmute" else "Mute",
                    onClick = {
                        muted = !muted
                        message = if (muted) "Mute selected" else "Unmute selected"
                    }
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFFFF666C), Color(0xFFD92F3C))
                                )
                            )
                            .clickable { message = "Power command selected" },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⏻", color = White, fontSize = 39.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Power", color = White, fontSize = 12.sp)
                }

                CircleControl(
                    symbol = "⇥",
                    label = "Input",
                    onClick = { message = "Input command selected" }
                )
            }

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SideControl(
                    title = "VOL",
                    onPlus = { message = "Volume up selected" },
                    onMinus = { message = "Volume down selected" },
                    modifier = Modifier.weight(0.7f)
                )

                DPad(
                    onCommand = { message = "$it selected" },
                    modifier = Modifier.weight(2.2f)
                )

                SideControl(
                    title = "CH",
                    onPlus = { message = "Channel up selected" },
                    onMinus = { message = "Channel down selected" },
                    modifier = Modifier.weight(0.7f)
                )
            }

            Spacer(Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircleControl("↶", "Back") {
                    message = "Back command selected"
                }
                CircleControl("⌂", "Home") {
                    message = "Home command selected"
                }
                CircleControl("⠿", "Menu") {
                    message = "Menu command selected"
                }
            }

            Spacer(Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "QUICK ACCESS",
                    color = Muted,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
                Spacer(Modifier.weight(1f))
                Text("4 shortcuts", color = Muted, fontSize = 10.sp)
            }

            Spacer(Modifier.height(13.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Shortcut("▶", "YouTube", Modifier.weight(1f)) {
                    message = "YouTube shortcut selected"
                }
                Shortcut("N", "Netflix", Modifier.weight(1f)) {
                    message = "Netflix shortcut selected"
                }
                Shortcut("a", "Prime", Modifier.weight(1f)) {
                    message = "Prime Video shortcut selected"
                }
                Shortcut("▦", "Apps", Modifier.weight(1f)) {
                    message = "Apps shortcut selected"
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Connect a TV to start controlling it",
                color = Muted,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun CircleButton(
    symbol: String,
    size: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(Panel)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(symbol, color = White, fontSize = 23.sp)
    }
}

@Composable
private fun CircleControl(
    symbol: String,
    label: String,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        listOf(PanelLight, Panel)
                    )
                )
                .border(1.dp, Color(0xFF303847), CircleShape)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(symbol, color = White, fontSize = 28.sp)
        }
        Spacer(Modifier.height(8.dp))
        Text(label, color = Muted, fontSize = 11.sp)
    }
}

@Composable
private fun DPad(
    onCommand: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF242B38), Color(0xFF11151D))
                )
            )
            .border(1.dp, Color(0xFF384152), CircleShape)
            .padding(8.dp)
    ) {
        CircleDPadButton("⌃", Modifier.align(Alignment.TopCenter)) {
            onCommand("Up")
        }
        CircleDPadButton("‹", Modifier.align(Alignment.CenterStart)) {
            onCommand("Left")
        }
        CircleDPadButton("›", Modifier.align(Alignment.CenterEnd)) {
            onCommand("Right")
        }
        CircleDPadButton("⌄", Modifier.align(Alignment.BottomCenter)) {
            onCommand("Down")
        }

        Box(
            modifier = Modifier
                .size(76.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Panel)
                .border(1.dp, Color(0xFF424D60), CircleShape)
                .clickable { onCommand("OK") },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "OK",
                color = White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun CircleDPadButton(
    symbol: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(symbol, color = White, fontSize = 25.sp)
    }
}

@Composable
private fun SideControl(
    title: String,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(205.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(
                Brush.verticalGradient(
                    listOf(PanelLight, Panel)
                )
            )
            .border(1.dp, Color(0xFF303847), RoundedCornerShape(26.dp))
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "+",
            color = White,
            fontSize = 27.sp,
            modifier = Modifier.clickable(onClick = onPlus)
        )
        Text(
            title,
            color = Muted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "−",
            color = White,
            fontSize = 27.sp,
            modifier = Modifier.clickable(onClick = onMinus)
        )
    }
}

@Composable
private fun Shortcut(
    symbol: String,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Panel)
            .border(1.dp, Color(0xFF303847), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            symbol,
            color = if (label == "YouTube") Red else White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(7.dp))
        Text(
            label,
            color = Muted,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
