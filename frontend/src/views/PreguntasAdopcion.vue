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
                            cycle
                            hide-delimiters
                            show-arrows-on-hover
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
        <v-card class="mt-4" :loading="loading">
            <v-card-text class="pt-4">
                <v-form v-model="formularioValido">
                    <v-row
                        no-gutters
                        v-for="respuesta in respuestas"
                        :key="respuesta.pregunta.id"
                    >
                        <v-col>
                            <v-text-field
                                outlined
                                dense
                                :rules="[value => (!!value || !respuesta.pregunta.obligatoria) || 'Esta pregunta es obligatoria']"
                                :label="respuesta.pregunta.texto"
                                v-model="respuesta.valor"
                            ></v-text-field>
                        </v-col>
                    </v-row>
                </v-form>
            </v-card-text>
        </v-card>
        <div class="mt-4 d-flex justify-end">
            <v-tooltip
                top
                :disabled="formularioValido"
            >
                <template v-slot:activator="{ on }">
                    <div v-on="on" class="d-inline-block">
                        <v-btn
                            color="primary"
                            @click="publicar"
                            :disabled="!formularioValido || loading"
                        >
                            Publicar
                        </v-btn>
                    </div>
                </template>
                <span>Debe completar todas las preguntas obligatorias</span>
            </v-tooltip>
        </div>
        <v-dialog
            v-model="exito"
            persistent
            max-width="900"
        >
            <v-card>
                <v-card-title>Publicacion Creada</v-card-title>
                <v-card-text>
                    <p>¡Se ha creado la publicacion correctamente!</p>
                    <p>Debe esperar a que sea validada para que la pueda ver</p>
                </v-card-text>
                <v-card-actions>
                    <v-btn text color="primary" to="/">Ir al inicio</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "PreguntasAdopcion",
    data: () => ({
        loading: false,
        exito: false,
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
        respuestas: [

        ],
        autor: {},
        formularioValido: false
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
                    },
                    error: (response) => {
                        console.log("error", response.data);
                    },
                    always: () => {
                        this.loading = false;
                        this.getPreguntas(this.mascota.organizacion_id);
                    }
                }
            }

            RequestHelper.get(request);
        },
        getPreguntas(idOrganizacion) {
            this.loading = true;

            const request = {
                url: `/api/organizacion/${idOrganizacion}/pregunta/activa`,
                handler: {
                    "200": (response) => {
                        let preguntas = response.data;

                        preguntas.forEach(pregunta => {
                            this.respuestas.push({
                                pregunta: pregunta,
                                valor: ''
                            });
                        })

                        console.log(preguntas);
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
        getAutor() {
            // Chequear si el usuario ya tiene datos de persona
            let token = this.$store.state.token;

            if(!token)
                return;

            this.loading = true;

            const request = {
                url: "/api/usuario",
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "200": (response) => {
                        let persona = response.data.persona;
                        this.autor = persona;
                    },
                    default: (response) => {
                        console.log("default", response.data)
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loading = false;
                        this.getMascota();
                    }
                }
            }

            RequestHelper.get(request);
        },
        publicar() {
            this.loading = true;

            let respuestas = ``;

            this.respuestas.forEach(respuesta => {
                respuestas += `
                    ${respuesta.pregunta.texto}\n
                    ${respuesta.valor}\n\n
                `
            });

            let especie = ``;

            if(this.mascota.especie === "Gato") {
                if(this.mascota.sexo === "Macho") {
                    especie = "Gato";
                } else {
                    especie = "Gata";
                }
            } else {
                if(this.mascota.sexo === "Macho") {
                    especie = "Perro";
                } else {
                    especie = "Perra";
                }
            }

            let body = {
                organizacion_id: this.mascota.organizacion_id,
                autor_id: this.autor.id,
                titulo: "Mascota en adopcion",
                cuerpo: `
                    Mi ${especie} ${this.mascota.nombre} ${this.mascota.sexo === "Macho" ? 'lo' : 'la'} estoy dando en adopcion, tiene ${this.mascota.edad} años.\n\n
                    ${respuestas}
                `,
                fotos: this.mascota.fotos,
                respuestas: this.respuestas,
                caracteristicas: this.mascota.caracteristicas
            };

            body.fotos.forEach(foto => {
                delete foto.id
            });

            let request = {
                url: `/api/publicacion/mascotaenadopcion`,
                body: body,
                handler: {
                    "200": () => {
                        this.exito = true;
                    },
                    default: (response) => {
                        console.log("default", response.data)
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            }

            RequestHelper.post(request);
        }
    },
    created() {
        if(!this.$route.params.id) {
            this.$router.push({name: 'dar-en-adopcion'});
            return;
        }

        this.getAutor();
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