import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Switch } from '@/components/ui/switch'
import { Badge } from '@/components/ui/badge'
import { Monitor, Smartphone, Laptop } from 'lucide-react'

export function SecurityContent() {
  return (
    <div className='space-y-6'>
      {/* 2FA Section */}
      <Card>
        <CardHeader>
          <CardTitle>Autenticación de dos factores (2FA)</CardTitle>
          <CardDescription>
            Añade una capa extra de seguridad a tu cuenta.
          </CardDescription>
        </CardHeader>
        <CardContent className='flex items-center justify-between'>
          <div className='space-y-1'>
            <p className='text-sm font-medium leading-none'>
              Aplicación autenticadora
            </p>
            <p className='text-sm text-muted-foreground'>
              Protege tu cuenta usando una app como Google Authenticator.
            </p>
          </div>
          <Switch />
        </CardContent>
      </Card>

      {/* Session History Section */}
      <Card>
        <CardHeader>
          <div className='flex items-center justify-between'>
            <div>
              <CardTitle>Historial de sesiones</CardTitle>
              <CardDescription>
                Dispositivos en los que has iniciado sesión recientemente.
              </CardDescription>
            </div>
            <Button variant='outline' size='sm'>
              Cerrar todas las demás sesiones
            </Button>
          </div>
        </CardHeader>
        <CardContent>
          <div className='space-y-4'>
            {/* Session 1 */}
            <div className='flex items-center justify-between border-b pb-4 last:border-0 last:pb-0'>
              <div className='flex items-center gap-4'>
                <div className='rounded-full bg-muted p-2'>
                  <Laptop className='h-4 w-4' />
                </div>
                <div>
                  <p className='text-sm font-medium leading-none flex items-center gap-2'>
                    Windows PC - Chrome
                    <Badge variant='secondary' className='h-5 text-[10px]'>Actual</Badge>
                  </p>
                  <p className='text-sm text-muted-foreground'>
                    Bogotá, Colombia • Dirección IP: 190.158.xx.xx
                  </p>
                  <p className='text-xs text-muted-foreground mt-1'>
                    Última actividad: hace un momento
                  </p>
                </div>
              </div>
            </div>

            {/* Session 2 */}
            <div className='flex items-center justify-between border-b pb-4 last:border-0 last:pb-0'>
              <div className='flex items-center gap-4'>
                <div className='rounded-full bg-muted p-2'>
                  <Smartphone className='h-4 w-4' />
                </div>
                <div>
                  <p className='text-sm font-medium leading-none'>
                    iPhone 14 Pro - Safari
                  </p>
                  <p className='text-sm text-muted-foreground'>
                    Medellín, Colombia • Dirección IP: 181.12.xx.xx
                  </p>
                  <p className='text-xs text-muted-foreground mt-1'>
                    Última actividad: ayer a las 3:45 PM
                  </p>
                </div>
              </div>
              <Button variant='ghost' size='sm' className='text-red-500 hover:text-red-600 hover:bg-red-100 dark:hover:bg-red-950'>
                Revocar
              </Button>
            </div>

            {/* Session 3 */}
            <div className='flex items-center justify-between border-b pb-4 last:border-0 last:pb-0'>
              <div className='flex items-center gap-4'>
                <div className='rounded-full bg-muted p-2'>
                  <Monitor className='h-4 w-4' />
                </div>
                <div>
                  <p className='text-sm font-medium leading-none'>
                    MacBook Pro - Firefox
                  </p>
                  <p className='text-sm text-muted-foreground'>
                    Desconocido • Dirección IP: 172.16.xx.xx
                  </p>
                  <p className='text-xs text-muted-foreground mt-1'>
                    Última actividad: 24 de abril, 2026
                  </p>
                </div>
              </div>
              <Button variant='ghost' size='sm' className='text-red-500 hover:text-red-600 hover:bg-red-100 dark:hover:bg-red-950'>
                Revocar
              </Button>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
