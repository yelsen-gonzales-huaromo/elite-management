import { Component, ErrorInfo, ReactNode } from 'react'
import { AlertTriangle } from 'lucide-react'
import { Button } from './ui/button'

interface Props {
  children?: ReactNode
  fallback?: ReactNode
}

interface State {
  hasError: boolean
}

export class ErrorBoundary extends Component<Props, State> {
  public state: State = {
    hasError: false,
  }

  public static getDerivedStateFromError(_: Error): State {
    return { hasError: true }
  }

  public componentDidCatch(error: Error, errorInfo: ErrorInfo) {
    console.error('Uncaught error:', error, errorInfo)
  }

  public render() {
    if (this.state.hasError) {
      return (
        this.props.fallback || (
          <div className='flex h-screen w-full flex-col items-center justify-center gap-4 p-4 text-center'>
            <AlertTriangle className='h-12 w-12 text-destructive' />
            <h2 className='text-2xl font-bold'>Algo salió mal</h2>
            <p className='text-muted-foreground'>
              La aplicación ha encontrado un error inesperado.
            </p>
            <Button
              onClick={() => window.location.reload()}
              variant='default'
            >
              Recargar página
            </Button>
          </div>
        )
      )
    }

    return this.props.children
  }
}
