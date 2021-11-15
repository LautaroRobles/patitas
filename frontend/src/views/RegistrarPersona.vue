<template>
    <v-container>
        <p class="text-h5 mt-4">Registro</p>
        <p>Antes de continuar necesitamos unos datos personales</p>
        <v-alert
            v-if="!logeado"
            type="warning"
        >
            No estas iniciado sesión, para tener acceso a esta información y cambiarla a futuro deberías
            <router-link class="text-decoration-none" :to="{name: 'registrar-usuario'}">crear una cuenta</router-link> o
            <router-link class="text-decoration-none" :to="{name: 'login'}">iniciar sesion</router-link>
        </v-alert>
        <v-card class="mt-6"
            :loading="loading"
            :disabled="loading"
        >
            <v-card-title>Datos de Persona</v-card-title>
            <v-card-text>
                <v-row>
                    <v-col>
                        <v-text-field
                            label="Nombre"
                            outlined
                            dense
                            v-model="datosDePersona.nombre"
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            label="Apellido"
                            outlined
                            dense
                            v-model="datosDePersona.apellido"
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-text-field
                    label="Fecha de Nacimiento"
                    outlined
                    dense
                    v-model="datosDePersona.fechaDeNacimiento"
                ></v-text-field>
                <v-row>
                    <v-col class="col-3">
                        <v-select
                            label="Tipo de Documento"
                            :items="tiposDeDocumento"
                            v-model="datosDePersona.tipoDeDocumento"
                            outlined
                            dense
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-text-field
                            label="Numero de Documento"
                            outlined
                            dense
                            v-model="datosDePersona.nroDocumento"
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-text-field
                    label="Direccion"
                    outlined
                    dense
                    v-model="datosDePersona.direccion"
                ></v-text-field>
                <v-text-field
                    label="Telefono"
                    outlined
                    dense
                    v-model="datosDePersona.telefono"
                ></v-text-field>
                <v-text-field
                    label="Email"
                    outlined
                    dense
                    v-model="datosDePersona.email"
                ></v-text-field>
            </v-card-text>
        </v-card>
        <v-card class="mt-4"
            :loading="loading"
            :disabled="loading"
        >
            <v-card-title>Datos de Contacto</v-card-title>
            <v-card-text>
                <v-row>
                    <template v-for="contacto in datosDeContacto">
                        <v-col class="col-12 col-sm-6 col-md-4" :key="contacto.key">
                            <v-sheet outlined color="grey" rounded>
                                <v-card flat>
                                    <v-card-text>
                                        <v-text-field
                                            class="mb-2"
                                            label="Nombre"
                                            outlined
                                            dense
                                            hide-details
                                            v-model="contacto.nombre"
                                        ></v-text-field>
                                        <v-text-field
                                            class="mb-2"
                                            label="Apellido"
                                            outlined
                                            dense
                                            hide-details
                                            v-model="contacto.apellido"
                                        ></v-text-field>
                                        <v-text-field
                                            class="mb-2"
                                            label="Telefono"
                                            outlined
                                            dense
                                            hide-details
                                            v-model="contacto.telefono"
                                        ></v-text-field>
                                        <v-text-field
                                            class="mb-2"
                                            label="Email"
                                            outlined
                                            dense
                                            hide-details
                                            v-model="contacto.email"
                                        ></v-text-field>
                                        <b>Formas de notificación preferidas</b>
                                        <v-row no-gutters>
                                            <v-col>
                                                <v-checkbox
                                                    class="mr-2"
                                                    label="Whatsapp"
                                                    value="Whatsapp"
                                                    hide-details
                                                    dense
                                                    v-model="contacto.tipoNotificaciones"
                                                ></v-checkbox>
                                            </v-col>
                                            <v-col>
                                                <v-checkbox
                                                    class="mr-2"
                                                    label="SMS"
                                                    value="SMS"
                                                    hide-details
                                                    dense
                                                    v-model="contacto.tipoNotificaciones"
                                                ></v-checkbox>
                                            </v-col>
                                            <v-col>
                                                <v-checkbox
                                                    label="Email"
                                                    value="Email"
                                                    hide-details
                                                    dense
                                                    v-model="contacto.tipoNotificaciones"
                                                ></v-checkbox>
                                            </v-col>
                                        </v-row>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-btn
                                            text
                                            block
                                            color="primary"
                                            @click="borrarContacto(contacto.key)"
                                        >
                                            <v-icon left>
                                                mdi-delete
                                            </v-icon>
                                            Borrar contacto
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-sheet>
                        </v-col>
                    </template>
                    <v-col class="col-12 col-sm-6 col-md-4">
                        <v-btn
                            class="d-flex"
                            style="height: 100%; min-height: 200px"
                            color="primary"
                            outlined
                            block
                            @click="crearContacto"
                        >Agregar contacto</v-btn>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
        <div class="mt-4 d-flex justify-end">
            <v-btn
                color="primary"
                @click="confirmarPersona"
            >
                Siguiente
            </v-btn>
        </div>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "RegistrarPersona",
    props: {
        goto: Object
    },
    data: () => ({
        logeado: false,
        loading: false,
        tiposDeDocumento: [
            "DNI",
            "Libreta",
            "No"
        ],
        datosDePersona: {
            nombre: "",
            apellido: "",
            fechaDeNacimiento: "",
            tipoDeDocumento: "",
            nroDocumento: "",
            direccion: "",
            telefono: "",
            email: ""
        },
        datosDeContacto: [
            {
                key: 0,
                nombre: "",
                apellido: "",
                telefono: "",
                email: "",
                tipoNotificaciones: []
            }
        ],
    }),
    methods: {
        crearContacto() {
            let lastKey = 0;
            if(this.datosDeContacto.length !== 0) {
                lastKey = this.datosDeContacto[this.datosDeContacto.length - 1].key + 1;
            }

            this.datosDeContacto.push({
                key: lastKey,
                nombre: "",
                apellido: "",
                telefono: "",
                email: "",
                tipoNotificaciones: []
            });
        },
        borrarContacto(key) {
            let contactoIndex = null;
            for(let i = 0; i < this.datosDeContacto.length; i++) {
                if(this.datosDeContacto[i].key === key) {
                    contactoIndex = i;
                }
            }

            if(contactoIndex != null)
                this.datosDeContacto.splice(contactoIndex, 1);
        },
        confirmarPersona() {
            this.loading = true;

            let body = this.datosDePersona;
            body.contactos = this.datosDeContacto;

            let token = this.$store.getters.getToken;

            const request = {
                url: "/api/persona",
                body: body,
                handler: {
                    "201": (response) => {
                        console.log(response.data);

                        let idPersona = response.data.id;

                        if(token) {
                            this.asociarAUsuario(idPersona);
                        }

                        this.goto.params =  { duenio_id: idPersona };
                        this.$router.push(this.goto);
                    },
                    "422": (response) => {
                        let message = response.data.message;

                        console.log(message);

                        this.error = true;
                        this.error_message = message;
                    },
                    default: (response) => {
                        console.log("default", response.data)
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        if(!token)
                            this.loading = false;
                    }
                }
            }

            RequestHelper.post(request);
        },
        asociarAUsuario(idPersona) {
            let token = this.$store.state.token;

            const request = {
                url: `/api/usuario/asociar/persona/${idPersona}`,
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "200": (response) => {
                        console.log(response.data);
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
    beforeMount() {
        // Chequear si el usuario ya tiene datos de persona
        let token = this.$store.state.token;

        if(!token)
            return;

        this.logeado = true;
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

                    if(persona) {
                        this.goto.params =  { duenio_id: persona.id };
                        this.$router.push(this.goto);
                    }
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
    watch: {
        '$store.state.token'() {
            this.logeado = !!this.$store.state.token;
        }
    }
}
</script>

<style scoped>

</style>