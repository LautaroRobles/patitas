<template>
    <v-container fluid class="fill-height pa-0">
        <v-carousel
            show-arrows-on-hover
            hide-delimiters
            height="600"
        >
            <v-carousel-item
                v-for="(imagen, index) in imagenes"
                :key="index"
                :src="imagen"
            >
            </v-carousel-item>
        </v-carousel>
        <div class="fill-height d-flex align-center justify-center bienvenido">
            <span class="text-h2 white py-2 px-4">
                Bienvenidos a Patitas
            </span>
        </div>
    </v-container>
</template>

<script>
export default {
    data: () => ({
        imagenes: [
            "https://cdn.discordapp.com/attachments/830832256931070035/915632647638765588/perro_-_gato.jpg",
            "https://cdn.discordapp.com/attachments/830832256931070035/915632415874101248/img_por_que_mi_perro_es_posesivo_con_los_juguetes_20719_600.jpg",
            "https://cdn.discordapp.com/attachments/830832256931070035/915632338937999401/Purina_Tips_para_elegir_juguetes_para_perros_y_gatos.png",
            "https://cdn.discordapp.com/attachments/830832256931070035/915632055801491536/50142b7d-674f-4d30-8e20-fcf03fa71eee_16-9-aspect-ratio_default_0.jpg"
        ]
    }),
}
</script>

<style>
.bienvenido {
    position: absolute;
    top:0;
    left: 0;
    width: 100%;
    pointer-events: none;
}
</style>