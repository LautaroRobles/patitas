<template>
    <v-container>
        <p class="text-h5 mt-4">Responde las siguientes preguntas para finalizar</p>
        <v-card class="mt-8"
            :loading="loading"
        >
            <v-card-text>
                <v-row>
                    <v-col class="col-12 col-sm-4 col-md-3" :style="'background-color:'+primary">
                        <v-carousel
                            height="300"
                            v-if="mascota.fotos.length > 0"
                        >
                            <v-carousel-item
                                v-for="(foto,i) in mascota.fotos"
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
                        <p class="mt-4 text-center text-h4 white--text">{{ mascota.nombre }}</p>
                    </v-col>
                    <v-col>
                        <v-card-text>
                            <v-row>
                                <v-col>
                                    <span>Apodo</span>
                                    <p class="text-h4">{{ mascota.apodo }}</p>
                                </v-col>
                                <v-col>
                                    <span>Especie</span>
                                    <p class="text-h4">{{ mascota.especie }}</p>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <span>Edad</span>
                                    <p class="text-h4">{{ mascota.edad }}</p>
                                </v-col>
                                <v-col>
                                    <span>Sexo</span>
                                    <p class="text-h4">{{ mascota.sexo }}</p>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <span>Descripcion</span>
                                    <p class="text-h4">{{ mascota.descripcion }}</p>
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
    name: "PreguntasAdopcion",
    data: () => ({
        loading: false,
        mascota: {
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
        },
        organizacion: {

        }
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

                        this.mascota = mascota;

                        console.log(mascota);

                        this.getOrganizacion(mascota.organizacion_id);
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
        },
        getOrganizacion(idOrganizacion) {
            this.loading = true;

            const request = {
                url: `/api/organizacion/${idOrganizacion}`,
                handler: {
                    "200": (response) => {
                        let organizacion = response.data;

                        this.organizacion = organizacion;

                        console.log(organizacion);
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