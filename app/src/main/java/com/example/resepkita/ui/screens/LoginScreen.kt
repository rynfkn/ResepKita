package com.example.resepkita.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resepkita.ui.theme.DarkCard
import com.example.resepkita.ui.theme.DarkInput
import com.example.resepkita.ui.theme.Green50
import com.example.resepkita.ui.theme.TextSecondary

@Composable
fun LoginScreen(
    onSignIn: (String, String) -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    var email by remember { mutableStateOf("chef@example.com") }
    var password by remember { mutableStateOf("password") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        // Logo
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(Green50, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("🔍", fontSize = 36.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Welcome back",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Sign in to your recipe book",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Email address") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = DarkInput,
                focusedContainerColor = DarkInput,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Green50,
                unfocusedPlaceholderColor = TextSecondary,
                focusedPlaceholderColor = TextSecondary,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Green50
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Password") },
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = DarkInput,
                focusedContainerColor = DarkInput,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Green50,
                unfocusedPlaceholderColor = TextSecondary,
                focusedPlaceholderColor = TextSecondary,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Green50
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Forgot password?",
            color = Green50,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In button
        Button(
            onClick = { onSignIn(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green50)
        ) {
            Text(
                "Sign In",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divider
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = DarkCard)
            Text(
                "  or continue with  ",
                color = TextSecondary,
                style = MaterialTheme.typography.bodySmall
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = DarkCard)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Social login buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { onSignIn(email, password) },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = DarkCard
                ),
                border = null
            ) {
                Text("🍎 Apple", color = Color.White)
            }
            OutlinedButton(
                onClick = { onSignIn(email, password) },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = DarkCard
                ),
                border = null
            ) {
                Text("🌐 Google", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Sign Up link
        Row(
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
            Text(
                "Sign Up",
                color = Green50,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }
    }
}
