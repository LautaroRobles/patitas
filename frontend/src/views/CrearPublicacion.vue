<template>
    <v-container>
        <v-slide-x-transition mode="out-in">
            <SeleccionarOrganizacion
                v-if="organizacion_id === null"
                @OrganizacionSeleccionada="organizacionSeleccionada"
            />
            <div v-else>
                <p class="text-h5 mt-8">
                    <v-btn icon class="mr-2" @click="organizacion_id = null">
                        <v-icon>mdi-arrow-left</v-icon>
                    </v-btn>
                    Crear Publicación
                </p>
                <v-row>
                    <v-col>
                        <v-card :loading="loading">
                            <v-card-text>
                                <v-select
                                    label="Seleccione categoria de publicación"
                                    outlined
                                    hide-details
                                    :items="categorias"
                                    @change="checkCategoria"
                                    v-model="categoria"
                                ></v-select>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-slide-x-transition mode="out-in">
                            <v-skeleton-loader
                                v-if="!categoria"
                                type="card-avatar, article, actions"
                            ></v-skeleton-loader>
                            <v-card
                                v-else
                                :loading="loading"
                            >
                                <v-card-title>Busco mascota para adoptar</v-card-title>
                                <v-card-text>
                                    <p class="mb-0">Preferencias de mascota</p>
                                    <template v-for="(caracteristica, index) in preferencias">
                                        <v-text-field
                                            :key="index"
                                            outlined
                                            dense
                                            :label="caracteristica.tipoCaracteristica.nombre"
                                            v-model="caracteristica.valor"
                                        ></v-text-field>
                                    </template>
                                    <p class="mb-0">Comodidades</p>
                                    <v-textarea
                                        outlined
                                        v-model="comodidades"
                                    >
                                    </v-textarea>
                                </v-card-text>
                            </v-card>
                        </v-slide-x-transition>
                    </v-col>
                </v-row>
                <div class="mt-4 d-flex justify-end">
                    <v-btn
                        color="primary"
                        @click="publicar"
                        :disabled="loading"
                    >
                        Publicar
                    </v-btn>
                </div>
            </div>
        </v-slide-x-transition>
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
import SeleccionarOrganizacion from "@/components/SeleccionarOrganizacion";

export default {
    name: "CrearPublicacion.vue",
    props: {
        persona_id: Number
    },
    components: {
        SeleccionarOrganizacion: SeleccionarOrganizacion
    },
    data: () => ({
        loading: false,
        exito: false,
        organizacion_id: null,
        categoria: null,
        categorias: [],
        preferencias: [],
        comodidades: "",
    }),
    methods: {
        checkCategoria(categoria) {
            switch (categoria) {
                case "Mascota perdida":
                    this.$router.push({name: "registrar-mascota-perdida"})
                    break;
                case "Mascota en adopcion":
                    this.$router.push({name: "dar-en-adopcion"})
                    break;
                default:
                    this.getCaracteristicasDisponibles()
                    break;
            }
        },
        organizacionSeleccionada(id) {
            this.organizacion_id = id;
        },
        getCategorias() {
            this.loading = true;

            let request = {
                url: "/api/publicacion/categoria",
                handler: {
                    "200": (response) => {
                        console.log(response.data);

                        this.categorias = response.data.map(categoria => {
                            return {
                                value: categoria.nombre,
                                text: categoria.nombre,
                                id: categoria.id,
                                nombre: categoria.nombre,
                                descripcion: categoria.descripcion
                            }
                        });
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

            RequestHelper.get(request);
        },
        getCaracteristicasDisponibles() {
            this.loading = true;

            if(!this.organizacion_id)
                return;

            let request = {
                url: `/api/organizacion/${this.organizacion_id}/`,
                handler: {
                    "200": (response) => {
                        let organizacion = response.data;

                        organizacion.caracteristicasDisponibles.forEach(tipoCaracteristica => {
                            this.preferencias.push({
                                tipoCaracteristica: tipoCaracteristica,
                                valor: ""
                            })
                        })
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
        publicar() {
            let categoria = this.categorias.filter(categoria => categoria.text === this.categoria);

            if(categoria.length > 0)
                categoria = categoria[0];
            else
                return;

            console.log(categoria);

            let preferencias = ``;

            this.preferencias.forEach(preferencia => {
                preferencias += `${preferencia.tipoCaracteristica.nombre}: ${preferencia.valor}\n`;
            })

            let body = {
                organizacion_id: this.organizacion_id,
                categoria: categoria,
                autor_id: this.persona_id,
                titulo: `Busco mascota para adoptar`,
                cuerpo: `Como preferencias quiero una mascota que tenga ${preferencias} \n y como comodidades: ${this.comodidades}`,
                preferencias: this.preferencias
            }

            let request = {
                url: `/api/publicacion/quieroadoptar`,
                body: body,
                handler: {
                    "201": () => {
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
        console.log(this.persona_id);

        if(!this.persona_id) {
            this.$router.push({name: "registrar-interesado"});
            return;
        }

        this.getCategorias();
    }
}
</script>

<style scoped>

</style>