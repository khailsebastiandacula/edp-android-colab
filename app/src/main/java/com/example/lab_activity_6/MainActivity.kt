package com.example.lab_activity_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val OrangeStart = Color(0xFFFF8A00)
private val OrangeEnd = Color(0xFFE65100)

private val OrangeGradient = Brush.horizontalGradient(
    colors = listOf(OrangeStart, OrangeEnd)
)

private val OrangeLightColorScheme = lightColorScheme(
    primary = OrangeStart,
    primaryContainer = Color(0xFFFFE0B2),
    onPrimaryContainer = Color(0xFF5D4037),
    background = Color(0xFFFFFDE7),
    surface = Color.White,
    onSurface = Color(0xFF1C1B1F),
    error = Color(0xFFD32F2F)
)

private val OrangeDarkColorScheme = darkColorScheme(
    primary = OrangeStart,
    primaryContainer = Color(0xFF4E2600),
    onPrimaryContainer = Color(0xFFFFDCC2),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE6E1E5),
    error = Color(0xFFCF6679)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = isSystemInDarkTheme()
            val currentColorScheme = if (isDarkMode) OrangeDarkColorScheme else OrangeLightColorScheme

            MaterialTheme(colorScheme = currentColorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GroceryListApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryListApp() {
    var newItem by remember { mutableStateOf("") }
    val groceries = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingBag,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                brush = OrangeGradient,
                                blendMode = BlendMode.SrcAtop
                            )
                        }
                    }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "My Grocery List",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newItem,
                    onValueChange = { newItem = it },
                    label = { Text("Enter an item") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = {
                        if (newItem.isNotBlank()) {
                            groceries.add(newItem.trim())
                            newItem = ""
                        }
                    },
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier.height(56.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(OrangeGradient, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Items List",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Box(
                modifier = Modifier
                    .background(OrangeGradient, shape = RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Total items: ${groceries.size}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (groceries.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your list is empty. Add some items above!",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(groceries) { item ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            IconButton(
                                onClick = { groceries.remove(item) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}