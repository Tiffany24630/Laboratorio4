/*
* Universidad del Valle de Guatemala
* PROGRAMACIÓN DE APLICACIONES MÓVILES
* Sección: 20
* Autora: Tiffany Salazar Suarez
* Carnét: 24630
* Link del video:
*/

package com.lab4.tiffanysalazar.ui

//Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab4.tiffanysalazar.R
import com.lab4.tiffanysalazar.ui.theme.Laboratorio4Theme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Laboratorio4Theme{
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)){
                        MascotaList()
                    }
                }
            }
        }
    }
}
data class Mascota(val name: String, val raza: String, val imagen: Int)

@Composable
fun MascotaCard(mascota: Mascota){
    var adoptado by remember {mutableStateOf(false)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = mascota.imagen),
                contentDescription = "Foto de: ${mascota.name}",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = mascota.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mascota.raza,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Button(onClick = {adoptado = !adoptado}){
                Text(if (adoptado) "¡Adoptado!️" else "Adoptar")
            }
        }
    }
}

@Composable
fun MascotaList(modifier: Modifier = Modifier){
    val mascotas = listOf(
        Mascota("Max", "Boxer", R.drawable.boxer),
        Mascota("Luna", "Chow Chow", R.drawable.chowchow),
        Mascota("Rocky", "Dalmata", R.drawable.dalmata)
    )

    LazyColumn(modifier = modifier.padding(8.dp)){
        items(items = mascotas) { mascota ->
            MascotaCard(mascota = mascota)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMascotaList(){
    Laboratorio4Theme{
        MascotaList()
    }
}