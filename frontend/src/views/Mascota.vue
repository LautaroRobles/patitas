<template>
    <v-container>
        <v-card class="mt-8"
            :loading="loading"
        >
            <v-card-text>
                <v-row>
                    <v-col class="col-12 col-sm-4 col-md-3" :style="'background-color:'+primary">
                        <v-carousel
                            height="300"
                            v-if="fotos.length > 0"
                        >
                            <v-carousel-item
                                v-for="(foto,i) in fotos"
                                :key="i"
                                :src="foto.imagenBase64"
                                reverse-transition="fade-transition"
                                transition="fade-transition"
                            ></v-carousel-item>
                        </v-carousel>
                        <v-carousel
                            height="300"
                            v-else
                        >
                            <v-carousel-item
                                :src="require('@/assets/sin_imagen.jpg')"
                                reverse-transition="fade-transition"
                                transition="fade-transition"
                            ></v-carousel-item>
                        </v-carousel>
                        <!--
                        <v-img :src="fotos.length > 0 ? fotos[0].imagenBase64 : require('@/assets/sin_imagen.jpg')"></v-img>
                        -->
                        <p class="mt-4 text-center text-h4 white--text">{{ nombre }}</p>
                    </v-col>
                    <v-col>
                        <v-card-text>
                            <v-row>
                                <v-col>
                                    <span>Apodo</span>
                                    <p class="text-h4">{{ apodo }}</p>
                                </v-col>
                                <v-col>
                                    <span>Especie</span>
                                    <p class="text-h4">{{ especie }}</p>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <span>Edad</span>
                                    <p class="text-h4">{{ edad }}</p>
                                </v-col>
                                <v-col>
                                    <span>Sexo</span>
                                    <p class="text-h4">{{ sexo }}</p>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <span>Descripcion</span>
                                    <p class="text-h4">{{ descripcion }}</p>
                                </v-col>
                            </v-row>
                        </v-card-text>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "Mascota.vue",
    data: () => ({
        loading: false,
        nombre: "",
        apodo: "",
        edad: "",
        especie: "",
        sexo: "",
        descripcion: "",
        id: "",
        duenio_id: "",
        organizacion_id: "",
        fotos: [],
        caracteristicas: [],
    }),
    methods: {
        getMascota() {
            this.loading = true;

            let idMascota = this.$route.params.id;

            const request = {
                url: `/api/mascota/${idMascota}`,
                handler: {
                    "200": (response) => {
                        let mascota = response.data;

                        this.id = mascota.id;
                        this.nombre = mascota.nombre;
                        this.apodo = mascota.apodo;
                        this.edad = mascota.edad;
                        this.especie = mascota.especie;
                        this.sexo = mascota.sexo;
                        this.descripcion = mascota.descripcion;
                        this.duenio_id = mascota.duenio_id;
                        this.fotos = mascota.fotos;
                        this.organizacion_id = mascota.organizacion_id;
                        this.caracteristicas = mascota.caracteristicas;

                        console.log(mascota);
                    },
                    error: (response) => {
                        console.log("error", response.data);
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            }

            RequestHelper.get(request);
        }
    },
    created() {
        this.getMascota();
    },
    computed: {
        primary() {
            return this.$vuetify.theme.currentTheme.primary;
        }
    }
}
</script>

<style scoped>

</style>