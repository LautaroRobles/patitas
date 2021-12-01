<template>
    <v-container>
        <v-slide-x-transition mode="out-in">
            <SeleccionarOrganizacion
                v-if="organizacion_id === null"
                @OrganizacionSeleccionada="organizacionSeleccionada"
            />
            <div v-else-if="organizacion">
                <v-card class="mt-8"
                    :loading="loadings.length > 0 || loading"
                >
                    <v-card-title>
                        <v-btn icon class="mr-2" @click="organizacion_id = null">
                            <v-icon>mdi-arrow-left</v-icon>
                        </v-btn>
                        {{organizacion.nombre}}
                    </v-card-title>
                    <v-card-text>
                        <p class="mb-0">Email: {{organizacion.email}}</p>
                        <p>Ubicacion: {{organizacion.ubicacion}}</p>
                        <v-expansion-panels multiple focusable>
                            <v-expansion-panel>
                                <v-expansion-panel-header>
                                    Caracteristicas
                                </v-expansion-panel-header>
                                <v-expansion-panel-content>
                                    <div
                                        class="pa-4"
                                    >
                                        <v-row
                                            class="align-center"
                                        >
                                            <v-col cols="10">
                                            </v-col>
                                            <v-col cols="2" class="d-flex justify-center">
                                                <span>Disponible</span>
                                            </v-col>
                                        </v-row>
                                        <v-row
                                            v-for="(caracteristica, index) in organizacion.caracteristicasDisponibles"
                                            :key="index"
                                            :class="`align-center ${!caracteristica.id ? 'warning' : ''} ${$vuetify.theme.dark ? 'darken-4' : 'lighten-5'}`"
                                        >
                                            <v-col cols="10">
                                                <v-text-field
                                                    outlined
                                                    dense
                                                    hide-details
                                                    label="Caracteristica"
                                                    v-model="caracteristica.nombre"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="2" class="d-flex justify-center">
                                                <v-switch
                                                    class="mt-0"
                                                    inset
                                                    dense
                                                    hide-details
                                                    v-model="caracteristica.disponible"
                                                ></v-switch>
                                            </v-col>
                                        </v-row>
                                        <v-row>
                                            <v-col>
                                                <v-btn block text color="primary" @click="crearCaracteristica(organizacion)">Crear Caracteristica</v-btn>
                                            </v-col>
                                        </v-row>
                                    </div>
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                            <v-expansion-panel>
                                <v-expansion-panel-header>
                                    Preguntas de adopcion
                                </v-expansion-panel-header>
                                <v-expansion-panel-content>
                                    <div
                                        class="pa-4"
                                    >
                                        <v-row
                                            class="align-center"
                                        >
                                            <v-col cols="8">
                                            </v-col>
                                            <v-col cols="2" class="d-flex justify-center">
                                                <span>Disponible</span>
                                            </v-col>
                                            <v-col cols="2" class="d-flex justify-center">
                                                <span>Obligatoria</span>
                                            </v-col>
                                        </v-row>
                                        <v-row
                                            v-for="(pregunta, index) in organizacion.preguntasAdopcion"
                                            :key="index"
                                            :class="`align-center ${!pregunta.id ? 'warning' : ''} ${$vuetify.theme.dark ? 'darken-4' : 'lighten-5'}`"
                                        >
                                            <v-col cols="8">
                                                <v-text-field
                                                    outlined
                                                    dense
                                                    hide-details
                                                    label="Pregunta"
                                                    v-model="pregunta.texto"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="2" class="d-flex justify-center">
                                                <v-switch
                                                    class="mt-0"
                                                    inset
                                                    dense
                                                    hide-details
                                                    v-model="pregunta.activo"
                                                ></v-switch>
                                            </v-col>
                                            <v-col cols="2" class="d-flex justify-center">
                                                <v-switch
                                                    class="mt-0 ml-0"
                                                    inset
                                                    dense
                                                    hide-details
                                                    v-model="pregunta.obligatoria"
                                                ></v-switch>
                                            </v-col>
                                        </v-row>
                                        <v-row>
                                            <v-col>
                                                <v-btn block text color="primary" @click="crearPregunta(organizacion)">Crear Pregunta</v-btn>
                                            </v-col>
                                        </v-row>
                                    </div>
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                        </v-expansion-panels>
                        <div class="d-flex justify-end">
                            <v-btn class="mt-4" color="primary" @click="confirmar">Confirmar cambios</v-btn>
                        </div>
                    </v-card-text>
                </v-card>
            </div>
        </v-slide-x-transition>
    </v-container>
</template>

<script>
import SeleccionarOrganizacion from "@/components/SeleccionarOrganizacion";
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "GestionarOrganizacion",
    components: {
        SeleccionarOrganizacion: SeleccionarOrganizacion
    },
    data: () => ({
        loading: false,
        loadings: [],
        organizacion_id: null,
        organizacion: {

        }
    }),
    methods: {
        organizacionSeleccionada(id) {
            this.organizacion_id = id;
        },
        crearCaracteristica(organizacion) {
            organizacion.caracteristicasDisponibles.push({
                nombre: "",
                disponible: true
            })
        },
        crearPregunta(organizacion) {
            organizacion.preguntasAdopcion.push({
                texto: "",
                activo: true,
                obligatoria: false
            })
        },
        getOrganizacion() {
            this.loading = true;

            if(!this.organizacion_id)
                return;

            let token = this.$store.state.token;

            let request = {
                url: `/api/organizacion/${this.organizacion_id}/admin`,
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "200": (response) => {
                        let organizacion = response.data;
                        this.organizacion = organizacion;
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
        confirmar() {
            this.organizacion.caracteristicasDisponibles.forEach(caracteristica => {
                switch (caracteristica.id) {
                    case undefined:
                        this.requestCrearCaracteristica(caracteristica, this.organizacion_id)
                        break;
                    default:
                        this.requestActualizarCaracteristica(caracteristica, this.organizacion_id)
                        break;
                }
            });
            this.organizacion.preguntasAdopcion.forEach(pregunta => {
                switch (pregunta.id) {
                    case undefined:
                        this.requestCrearPregunta(pregunta, this.organizacion_id)
                        break;
                    default:
                        this.requestActualizarPregunta(pregunta, this.organizacion_id)
                        break;
                }
            });
        },
        requestCrearCaracteristica(caracteristica, organizacion_id) {
            this.loadings.push(true);

            let token = this.$store.state.token;

            let request = {
                url: `/api/organizacion/${organizacion_id}/caracteristica`,
                body: caracteristica,
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "201": (response) => {
                        let caracteristicaResponse = response.data;
                        caracteristica.id = caracteristicaResponse.id;
                        console.log(response);
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loadings.pop();
                    }
                }
            }

            RequestHelper.post(request);
        },
        requestActualizarCaracteristica(caracteristica, organizacion_id) {
            this.loadings.push(true);

            let token = this.$store.state.token;

            let request = {
                url: `/api/organizacion/${organizacion_id}/caracteristica/${caracteristica.id}`,
                body: caracteristica,
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "200": (response) => {
                        console.log(response);
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loadings.pop();
                    }
                }
            }

            RequestHelper.post(request);
        },
        requestCrearPregunta(pregunta, organizacion_id) {
            this.loadings.push(true);

            let token = this.$store.state.token;

            let request = {
                url: `/api/organizacion/${organizacion_id}/pregunta`,
                body: pregunta,
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "201": (response) => {
                        let preguntaResponse = response.data;
                        pregunta.id = preguntaResponse.id;
                        console.log(response);
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loadings.pop();
                    }
                }
            }

            RequestHelper.post(request);
        },
        requestActualizarPregunta(pregunta, organizacion_id) {
            this.loadings.push(true);

            let token = this.$store.state.token;

            let request = {
                url: `/api/organizacion/${organizacion_id}/pregunta/${pregunta.id}`,
                body: pregunta,
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "200": (response) => {
                        console.log(response);
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loadings.pop();
                    }
                }
            }

            RequestHelper.post(request);
        }
    },
    watch: {
        'organizacion_id'() {
            this.getOrganizacion();
        }
    }
}
</script>

<style scoped>

</style>