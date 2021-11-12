<template>
    <v-container fluid fill-height class="justify-center">
        <v-card width="600">
            <v-card-title>Crear cuenta</v-card-title>
            <v-card-text>
                <v-form
                    @submit.prevent="registrar"
                >
                    <v-row>
                        <v-col>
                            <v-text-field
                                outlined
                                dense
                                label="Email"
                                v-model="email"
                            >
                            </v-text-field>
                            <v-text-field
                                outlined
                                dense
                                label="Usuario"
                                v-model="username"
                            >
                            </v-text-field>
                            <v-text-field
                                type="password"
                                outlined
                                dense
                                label="Contraseña"
                                v-model="password"
                            >
                            </v-text-field>
                        </v-col>
                        <v-col class="col-5 d-flex justify-center align-center">
                            <div>
                                <v-btn
                                    type="submit"
                                    color="primary"
                                    block
                                >
                                    Crear cuenta
                                </v-btn>
                                <p class="mt-2">¿Ya tenés una cuenta? <router-link class="text-decoration-none" to="/login">Inicia sesion</router-link></p>
                            </div>
                        </v-col>
                    </v-row>
                </v-form>
            </v-card-text>
            <v-overlay
                :value="loading"
                absolute
            >
                <v-progress-circular
                    indeterminate
                ></v-progress-circular>
            </v-overlay>
        </v-card>
        <v-snackbar
            v-model="error"
            :timeout="2000"
            top
            color="error darken-3"
        >
            {{ error_message }}
        </v-snackbar>
        <v-dialog
            v-model="dialog"
            persistent
            max-width="290"
        >
            <v-card>
                <v-card-title>¡Registro exitoso!</v-card-title>
                <v-card-text>
                    El usuario fue creado exitosamente, ¿quiere iniciar sesión?
                </v-card-text>
                <v-card-actions>
                    <v-btn text color="primary" to="/login">Iniciar sesion</v-btn>
                    <v-btn text to="/">Ir al inicio</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "RegistrarUsuario.vue",
    data: () => ({
        loading: false,
        error: false,
        error_message: "",
        dialog: false,
        email: "",
        username: "",
        password: ""
    }),
    methods: {
        registrar() {
            this.loading = true;

            const request = {
                url: "/api/usuario",
                body: {
                    email: this.email,
                    username: this.username,
                    password: this.password
                },
                handler: {
                    "201": (response) => {
                        console.log(response)
                        this.dialog = true;
                    },
                    "422": (response) => {
                        let message = response.data.message;

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
                        this.loading = false;
                    }
                }
            }

            RequestHelper.post(request);
        }
    }
}
</script>

<style scoped>

</style>